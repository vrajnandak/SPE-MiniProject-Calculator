pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vrajnandak/SPE-MiniProject-Calculator.git'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Example if you’re using Python unittest:
                // sh 'python3 -m unittest discover -s tests'
            }
        }
    }
}

