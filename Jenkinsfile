pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "shravan001/weather-app-demo"
        DOCKER_TAG = "latest"
        TOMCAT_DIR = "/opt/tomcat9/webapps"
        WAR_FILE = "target/weather-app-1.0-SNAPSHOT.war"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/Shravan-kumar00/weather-app-demo.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Perform SonarQube analysis
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }

        stage('Security Scan with Trivy') {
            steps {
                script {
                    // Trivy security scan on the WAR file
                    sh 'trivy fs . --exit-code 1 --severity HIGH,CRITICAL --no-progress'
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    // Copy the WAR file to Tomcat
                    sh "scp ${WAR_FILE} user@your-server-ip:${TOMCAT_DIR}"

                    // Restart Tomcat to deploy the new WAR file
                    sh "ssh user@your-server-ip 'sudo systemctl restart tomcat9'"
                }
            }
        }
    }

    post {
        failure {
            echo "Pipeline failed. Check the logs for details."
        }
    }
}
