pipeline {
    agent any
    
    environment {
        NEXUS_CREDENTIALS_ID = 'NEXUS_LOGIN'
    }
    
    stages {
        stage('Checkout') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'github', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        git branch: 'main', credentialsId: 'github', url: 'https://github.com/AshrafElshazly/java-ci'
                    }
                }
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        
        stage('Unit Test') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/**/*.xml'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    sh '''mvn clean verify sonar:sonar -Dsonar.projectKey=ProjectNameSonar -Dsonar.projectName='java-app' -Dsonar.host.url=http://localhost:9000'''
                    echo 'SonarQube Analysis Completed'
                }
            }
        }
            
        stage('Store Artifact') {
            steps {
                nexusArtifactUploader(
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    nexusUrl: '11.0.0.100:8081/',
                    groupId: 'Dev',
                    version: "${env.BUILD_ID}-${env.BUILD_TIMESTAMP}",
                    repository: 'node-app',
                    credentialsId: NEXUS_CREDENTIALS_ID,
                    artifacts: [
                        [artifactId: 'node',
                        classifier: '',
                        file: 'target/demo-0.0.1-SNAPSHOT.jar',
                        type: 'jar']
                    ]
                )
            }
        }
    }
    
    post {
        always {
            deleteDir()
        }
    }
}
