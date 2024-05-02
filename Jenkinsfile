pipeline {
    agent any
    
    environment {
        NEXUS_CREDENTIALS_ID = 'NEXUS_LOGIN'
        AWS_ACCESS_KEY_ID     = credentials('aws-access-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
        AWS_SESSION_TOKEN     = credentials('aws-session-token')
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

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("471112706112.dkr.ecr.us-east-1.amazonaws.com/java-app:${env.BUILD_ID}")
                }
            }
        }

        stage('Push and Scan Image ECR') {
            steps {
                withCredentials([
                    string(credentialsId: 'aws-access-key-id', variable: 'AWS_ACCESS_KEY_ID'),
                    string(credentialsId: 'aws-secret-access-key', variable: 'AWS_SECRET_ACCESS_KEY'),
                    string(credentialsId: 'aws-session-token', variable: 'AWS_SESSION_TOKEN')
                ]) {
                    sh """
                    export AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}
                    export AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY}
                    export AWS_SESSION_TOKEN=${AWS_SESSION_TOKEN}
                    aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 471112706112.dkr.ecr.us-east-1.amazonaws.com
                    """
                    script {
                    docker.image("471112706112.dkr.ecr.us-east-1.amazonaws.com/java-app:latest").push()
                    }
                }
            }
        }
    }
    
    post {
        always {
            sh 'echo "Done ^_^"'
        //    deleteDir()
        //    sh 'docker system prune -af'
        //    sh 'docker system prune -f'
        }
    }
}
