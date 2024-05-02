@Library('shared-library') _

pipeline {
    agent any
    
    environment {
        NEXUS_CREDENTIALS_ID = 'NEXUS_LOGIN'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
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
            sonarAnalysis()
        }
    }
        
        stage('Store Artifact') {
            steps {
                storeArtifact()
            }
        }

        stage('Build Docker Image') {
            steps {
                buildDockerImage()
            }
        }

        stage('Push and Scan Image ECR') {
            steps {
                pushAndScanECRImage()
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed successfully!'
        }
    }
}
