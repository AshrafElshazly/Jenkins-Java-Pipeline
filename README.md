# Java CI/CD Pipeline with Jenkins

## AWS Infrastructure

![AWS Infrastructure](/images/infra-diagram.jpg)

## Pipeline

![Pipeline](/images/pipeline-diagram.jpg)

This project demonstrates a continuous integration and continuous delivery (CI/CD) pipeline for a Java application using Jenkins. The pipeline automates the build, test, static code analysis, and artifact deployment processes.

## Prerequisites

Before setting up the pipeline, ensure the following prerequisites are met:

- AWS EC2 instances are provisioned with Ubuntu: one public for Jenkins, one private for SonarQube, and one private for Nexus Repository.
- Jenkins server is set up and accessible.
- SonarQube server is installed and configured for static code analysis.
- Sonatype Nexus Repository Manager is installed and configured.

## Pipeline Overview

The Jenkins pipeline consists of several stages:

1. **Checkout**: This stage checks out the code from the GitHub repository.
2. **Build**: Maven builds the project.
3. **Unit Test**: Maven runs unit tests and collects test results.
4. **SonarQube Analysis**: Static code analysis is performed using SonarQube.
5. **Store Artifact**: The built artifact (JAR file) is uploaded to the Nexus Repository.
6. **Post-build**: Cleanup tasks are executed, including workspace deletion.

## How to Use

Follow these steps to use the CI/CD pipeline:

1. **Configure Jenkins**: Set up Jenkins with the required plugins and configurations.
2. **Configure SonarQube**: Configure SonarQube server and obtain the necessary credentials.
3. **Configure Nexus Repository**: Set up Nexus Repository Manager and configure the repository.
4. **Set Jenkins Credentials**: Add credentials for GitHub, Nexus Repository, and SonarQube to Jenkins.
5. **Create Jenkins Pipeline**: Create a new pipeline in Jenkins and paste the provided Jenkinsfile.
6. **Set up Webhook**: In your GitHub repository settings, navigate to "Webhooks" and add a new webhook pointing to your Jenkins server's URL. Configure the webhook to trigger on code push events. Ensure that the Jenkins GitHub plugin is installed and configured to receive webhook notifications.
7. **Run Pipeline**: Trigger the pipeline manually or set up a webhook for automatic triggering on code changes.

## Additional Images

- **Webhook Configuration**:
  ![Webhook Configuration](/images/webhook.png)

- **Jenkins Stage View**:
  ![Jenkins Stage View](/images/stage-view.png)

- **Artifact Upload in Nexus Repository**:
  ![Artifact Upload](/images/artifact.png)

## AWS Infrastructure

The project assumes the following AWS infrastructure setup:

- **Jenkins Server**: Public EC2 instance accessible from the internet.
- **SonarQube Server**: Private EC2 instance accessible only from the Jenkins server.
- **Nexus Repository Server**: Private EC2 instance accessible only from the Jenkins server.

Ensure proper security group configurations and network access to these instances.
