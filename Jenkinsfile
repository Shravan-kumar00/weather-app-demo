pipeline {
    agent any

    environment {
        ARTIFACTORY_URL = 'https://your-jfrog-url/artifactory'
        CREDENTIALS_ID = 'jfrog-credentials-id'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'demo', url: 'https://github.com/Shravan-kumar00/wether-app-demo.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Running Maven build...'
                sh 'mvn clean package' 
            }
        }

        stage('Push to JFrog') {
            steps {
                echo 'Uploading artifact to JFrog...'

                sh '''
                    curl -u $CREDENTIALS_ID -T target/sample.jar $ARTIFACTORY_URL/weather-app-demo/sample.jar
                '''
            }
        }
    }
}
