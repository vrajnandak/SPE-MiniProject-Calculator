pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub-cred'
        IMAGE_NAME = 'vrajnandak/scientific-calculator'
        IMAGE_TAG = 'latest'
    }

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vrajnandak/SPE-MiniProject-Calculator.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t $IMAGE_NAME:$IMAGE_TAG ."
                }
            }
        }
	
	stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        sh "docker push $IMAGE_NAME:$IMAGE_TAG"
                    }
                }
            }
        }
    }
    
    post {
        always {
	    sh 'docker logout'
            echo 'Pipeline finished'
        }
    }
}

