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

        stage('run') {
            steps {
                script {
                    sh  "./mvnw spring-boot:run &"
                    sh  "sleep 10"
                }
            }
        }

        stage('Integration Test') {
            steps {
                nodejs(nodeJSInstallationName: 'nodejs') {
                    sh  "newman run Dxc.postman_collection.json"
                }
            }
        }

        stage('selenium') {
            steps {
                script {
                    sh "rm -rf TrabajoFinalM4Selenium"
                    sh "git clone --single-branch --branch main https://github.com/udd-seccion2-grupo4/TrabajoFinalM4Selenium"
                    sh "cd TrabajoFinalM4Selenium; ./mvnw test"
                }
            }
        }
    }
}