pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'weather-app'
        DOCKER_REGISTRY = 'your-docker-registry' // Docker registry (e.g., Docker Hub)
        SONARQUBE_SERVER = 'SonarQube' // Name of SonarQube server configured in Jenkins
        SONARQUBE_TOKEN = credentials('sonar-token') // Use Jenkins credentials store to keep token safe
        TRIVY_VERSION = 'v0.25.0' // Specify Trivy version
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the project code from the repository
                git 'https://github.com/your-repo/weather-app.git'
            }
        }

        stage('Build Java Project') {
            steps {
                script {
                    // Build the project (assuming Maven is used)
                    sh 'mvn clean install'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Run SonarQube scan
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn sonar:sonar -Dsonar.login=$SONARQUBE_TOKEN'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    sh 'docker build -t $DOCKER_REGISTRY/$DOCKER_IMAGE .'
                }
            }
        }

        stage('Trivy Vulnerability Scan') {
            steps {
                script {
                    // Install Trivy (if not installed)
                    sh 'curl -sfL https://github.com/aquasecurity/trivy/releases/download/$TRIVY_VERSION/trivy_$TRIVY_VERSION_Linux-64bit.tar.gz | tar xz -C /usr/local/bin'

                    // Run Trivy scan on the Docker image
                    sh 'trivy image --no-progress --exit-code 1 --severity HIGH,CRITICAL $DOCKER_REGISTRY/$DOCKER_IMAGE'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Log in to Docker Hub (if using Docker Hub)
                    sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'

                    // Push the image to the Docker registry
                    sh 'docker push $DOCKER_REGISTRY/$DOCKER_IMAGE'
                }
            }
        }

        stage('Deploy to Production') {
            steps {
                script {
                    // Deploy the Docker container (adjust this step as per your deployment method)
                    sh 'docker run -d -p 8080:8080 --name weather-app $DOCKER_REGISTRY/$DOCKER_IMAGE'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker images and resources
            sh 'docker system prune -f'
        }
    }
}
