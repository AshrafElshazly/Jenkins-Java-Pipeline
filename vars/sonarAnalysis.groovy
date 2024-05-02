#!/usr/bin/env groovy

def call() {
    withSonarQubeEnv('SonarQubeServer') {
        sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=ProjectNameSonar -Dsonar.projectName="java-app" -Dsonar.host.url=http://11.0.0.101:9000'
    }
}
