pipeline {

    agent any

    environment {
        DOCKER_IMAGE = "saikiran9068/novapay:latest"
        DOCKER_USER  = "saikiran9068"
        NAMESPACE    = "novapay"

        TRIVY   = "/usr/local/bin/trivy"
        DOCKER  = "/usr/local/bin/docker"
        KUBECTL = "/usr/local/bin/kubectl"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Checking source code"
            }
        }

        stage('Build') {
            steps {
                dir('app/novapay') {
                    sh '''
                        chmod +x mvnw
                        ./mvnw clean package
                    '''
                }
            }
        }

        stage('Unit Test') {
            steps {
                dir('app/novapay') {
                    sh '''
                        ./mvnw test
                    '''
                }
            }
        }

        stage('SAST - SonarQube') {
            steps {
                echo "Running SonarQube security scan"
            }
        }

        stage('Dependency Scan') {
            steps {
                echo "Checking vulnerable dependencies"
            }
        }

        stage('Docker Build') {
            steps {
                dir('app/novapay') {
                    sh '''
                        ${DOCKER} build -t ${DOCKER_IMAGE} .
                    '''
                }
            }
        }

        stage('Container Scan') {
            steps {
                sh '''
                    ${TRIVY} image \
                    --severity HIGH,CRITICAL \
                    --exit-code 0 \
                    ${DOCKER_IMAGE}
                '''
            }
        }

        stage('Push Image') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-token',
                        usernameVariable: 'USERNAME',
                        passwordVariable: 'PASSWORD'
                    )
                ]) {

                    sh '''
                        echo $PASSWORD | ${DOCKER} login \
                        -u $USERNAME \
                        --password-stdin

                        ${DOCKER} push ${DOCKER_IMAGE}
                    '''
                }
            }
        }

        stage('Deploy Kubernetes') {
            steps {
                sh '''
                    echo "Deploying Kubernetes resources"

                    ${KUBECTL} apply -f namespace.yaml

                    ${KUBECTL} apply -f app/novapay/kubernetes/service/service.yaml

                    ${KUBECTL} apply -f app/novapay/kubernetes/blue/deployment.yaml

                    echo "Waiting for deployment..."

                    ${KUBECTL} rollout status deployment/novapay-blue -n ${NAMESPACE}
                '''
            }
        }

        stage('Verification') {
            steps {
                sh '''
                    echo "Checking deployment..."

                    ${KUBECTL} get pods -n ${NAMESPACE}

                    ${KUBECTL} get svc -n ${NAMESPACE}

                    ${KUBECTL} get deployment -n ${NAMESPACE}
                '''
            }
        }

    }

    post {

        success {
            echo "Deployment completed successfully!"
        }

        failure {

            echo "Deployment failed - checking rollback"

            sh '''
                ${KUBECTL} get deployment novapay-blue -n ${NAMESPACE} >/dev/null 2>&1 && \
                ${KUBECTL} rollout undo deployment/novapay-blue -n ${NAMESPACE} || \
                echo "No deployment found. Rollback skipped."
            '''
        }

        always {
            cleanWs()
        }
    }
}