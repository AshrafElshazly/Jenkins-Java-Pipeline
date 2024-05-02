#!/usr/bin/env groovy

def call() {
    nexusArtifactUploader(
        nexusVersion: 'nexus3',
        protocol: 'http',
        nexusUrl: '11.0.0.100:8081/',
        groupId: 'Dev',
        version: "${env.BUILD_ID}-${env.BUILD_TIMESTAMP}",
        repository: 'node-app',
        credentialsId: env.NEXUS_CREDENTIALS_ID,
        artifacts: [
            [artifactId: 'node',
            classifier: '',
            file: 'target/demo-0.0.1-SNAPSHOT.jar',
            type: 'jar']
        ]
    )
}
