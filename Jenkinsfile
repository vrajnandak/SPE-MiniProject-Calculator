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
    success {
        echo "✅ Build, push, and deployment successful!"
        mail to: "Vrajnandak Nangunoori <vrajnandaknangunoori@gmail.com>",
            subject: "✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """\
Hello Vrajnandak,

🎉 The Jenkins pipeline completed successfully!

🔹 Job: ${env.JOB_NAME}
🔹 Build Number: ${env.BUILD_NUMBER}
🔹 Branch: ${env.BRANCH_NAME ?: 'main'}
🔹 Duration: ${currentBuild.durationString ?: 'N/A'}

View the build details here:
${env.BUILD_URL}

Console output:
${env.BUILD_URL}console

-- 
Sent automatically by Jenkins CI/CD
"""
    }

    failure {
        echo "❌ Build or deployment failed. Check console output."
        mail to: "Vrajnandak Nangunoori <vrajnandaknangunoori@gmail.com>",
            subject: "❌ FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """\
Hello Vrajnandak,

💥 The Jenkins pipeline has failed.

🔹 Job: ${env.JOB_NAME}
🔹 Build Number: ${env.BUILD_NUMBER}
🔹 Branch: ${env.BRANCH_NAME ?: 'main'}
🔹 Duration: ${currentBuild.durationString ?: 'N/A'}

View the build details here:
${env.BUILD_URL}

Check the console output for more information:
${env.BUILD_URL}console

-- 
Sent automatically by Jenkins CI/CD
"""
    }

    always {
        echo "📬 Email notification attempted based on build result."
    }
}


}

