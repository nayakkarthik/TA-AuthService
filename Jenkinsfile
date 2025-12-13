pipeline {
    agent any
    tools {
        maven 'mvn-3.9.11'  // Configure in Global Tool Config
        jdk 'jdk-21'
    }
      
    stages {
        stage('Check Java') {
            steps {
                sh 'java -version'
            }
        }
        stage('Checkout') { steps { checkout scm } }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Build & Run') {
            steps {
                script {
            
                    sh """
                        docker build -t authservice:${BUILD_NUMBER} .
                        docker stop authservice || true
                        docker rm authservice || true
                        docker run -d --name authservice -p 8081:8080 authservice:${env.BUILD_NUMBER}
                    """
                }
            }
        }
    }
}
