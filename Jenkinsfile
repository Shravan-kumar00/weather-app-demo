pipeline {
    agent any

    environment {
        // Replace with your actual Artifactory URL and credentials ID
        ARTIFACTORY_URL = 'https://your-jfrog-url/artifactory'
        CREDENTIALS_ID = 'jfrog-credentials-id'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'demo', url: 'https://github.com/Shravan-kumar00/End-to-End-Kubernetes-Three-Tier-DevSecOps-Project.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Running Maven build...'
                sh 'mvn clean package -DskipTests=true' // Fake build step
            }
        }

        stage('Push to JFrog') {
            steps {
                echo 'Uploading artifact to JFrog...'

                // Simulate artifact upload — replace with actual curl, jfrog CLI, or plugin-based upload
                sh '''
                    curl -u $CREDENTIALS_ID -T target/sample.jar $ARTIFACTORY_URL/your-repo/sample.jar
                '''
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}
