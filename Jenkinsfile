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
        script {
            def jobName = env.JOB_NAME
            def buildNumber = env.BUILD_NUMBER
            def buildUrl = env.BUILD_URL
            def branchName = env.BRANCH_NAME ?: 'main'
            def duration = currentBuild.durationString ?: 'N/A'
            def status = currentBuild.currentResult ?: 'UNKNOWN'

            // Color banner based on build status
            def bannerColor = (
                status == 'SUCCESS' ? '#28a745' : 
                status == 'FAILURE' ? '#dc3545' : 
                status == 'UNSTABLE' ? '#ffc107' : 
                '#6c757d'
            )

            // Construct email body with HTML formatting
            def body = """
                <html>
                    <body style="font-family: Arial, sans-serif;">
                        <div style="border: 3px solid ${bannerColor}; border-radius: 8px; padding: 20px;">
                            <h2 style="color: ${bannerColor};">Jenkins Build Report</h2>
                            <h3>${jobName} - Build #${buildNumber}</h3>

                            <div style="background-color: ${bannerColor}; color: white; padding: 10px; border-radius: 4px;">
                                <strong>Pipeline Status:</strong> ${status.toUpperCase()}
                            </div>

                            <p style="margin-top: 15px;">
                                <strong>Details:</strong><br>
                                🌿 <b>Branch:</b> ${branchName}<br>
                                ⏱️ <b>Duration:</b> ${duration}<br>
                                🔗 <b>Build URL:</b> <a href="${buildUrl}" style="color: #007bff;">View in Jenkins</a><br>
                                🖥️ <b>Console Output:</b> <a href="${buildUrl}console" style="color: #007bff;">View Logs</a>
                            </p>

                            <p style="margin-top: 20px; color: #555;">
                                -- <br>
                                <i>Sent automatically by Jenkins CI/CD Server</i>
                            </p>
                        </div>
                    </body>
                </html>
            """

            // Send email notification
            emailext(
                    subject: "📦 ${jobName} #${buildNumber} - ${status.toUpperCase()}",
                    body: body,
                    mimeType: 'text/html',
                    to: "vrajnandaknangunoori@gmail.com",
		    from: "vrajnandaknangunoori@gmail.com",
		    replyTo: "vrajnandaknangunoori@gmail.com"
	    )

                echo "✅ Email notification sent for build #${buildNumber} (${status})."
            }
        }
    }

}

