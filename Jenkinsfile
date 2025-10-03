pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vrajnandak/SPE-MiniProject-Calculator.git'
            }
        }

        stage('List Files') {
            steps {
                echo "Listing all files in the repository..."
                sh 'ls -R'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                // Example if you have Python tests:
                // sh 'python3 -m unittest discover -s tests'
            }
        }
    }
}

