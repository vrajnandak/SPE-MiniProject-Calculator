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
		echo 'Cloning repository...'
                git branch: 'main', url: 'https://github.com/vrajnandak/SPE-MiniProject-Calculator.git'
            }
        }
	
	stage('Check Tools') {
	    steps {
		echo 'Checking Docker and Maven versions...'
		sh '''
		    docker --version
		    mvn --version
		'''
	    }
	}

        stage('Run Tests') {
            steps {
                echo 'Running unit tests using Maven...'
                sh 'mvn clean test'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image using multi-stage Dockerfile...'
		sh "docker build -t $IMAGE_NAME:$IMAGE_TAG ."
            }
        }

	stage('Push Docker Image to DockerHub') {
            steps {
                script {
		    echo 'Pushing Docker image to Docker Hub...'
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        sh "docker push $IMAGE_NAME:$IMAGE_TAG"
                    }
                }
            }
        }

	stage('Deploy via Ansible') {
            steps {
                echo 'Deploying application using Ansible...'
                sh 'ansible-playbook ansible/deploy-docker.yml'
            }
        }        

    }
    
    post {
        always {
	    echo 'Cleaning up...'
	    sh 'docker logout || true'
	    echo "Pipeline finished: ${currentBuild.result ?: 'SUCCESS'}"
        }
	failure {
	    echo 'Pipeline failed'
	}
    }
}

