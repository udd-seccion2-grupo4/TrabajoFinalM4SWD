pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                script {
                        sh  "./mvnw clean"      
                }
            }
        }

        stage('Compile') {
            steps {
                script {
                        sh  "./mvnw compile -e"
                }
            }
        }

        stage('Unit Test') {
            steps {
                 script {
                    sh  "./mvnw test"      
                }
            }
        }

        stage('Integration Test') {
            steps {
                 script {
                    sh  "newman run Dxc.postman_collection.json"
                }
            }
        }
    }
}