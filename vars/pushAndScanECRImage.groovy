#!/usr/bin/env groovy

def call() {
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
        docker.image('471112706112.dkr.ecr.us-east-1.amazonaws.com/java-app:latest').push()
    }
}
