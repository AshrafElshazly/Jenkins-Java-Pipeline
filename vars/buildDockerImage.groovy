#!/usr/bin/env groovy

def call() {
    docker.build("471112706112.dkr.ecr.us-east-1.amazonaws.com/java-app:${env.BUILD_ID}").push()
}
