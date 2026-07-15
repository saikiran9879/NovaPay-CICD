# NovaPay CI/CD Pipeline with Kubernetes Deployment

## Project Overview

NovaPay is a fictional RBI-licensed digital banking application used to demonstrate a production-style DevOps CI/CD pipeline. This project automates application build, testing, containerization, security scanning, Docker image publishing, and Kubernetes deployment using Jenkins.

---

## Project Objectives

- Automate CI/CD using Jenkins
- Build Java Spring Boot application using Maven
- Build Docker image
- Perform container vulnerability scanning using Trivy
- Push Docker image to Docker Hub
- Deploy application to Kubernetes
- Support Blue-Green deployment strategy
- Support Canary deployment strategy
- Verify successful deployment automatically

---

## Technology Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 4.1.0 |
| Maven | Wrapper |
| Docker | Latest |
| Jenkins | Latest |
| Kubernetes | Minikube |
| Trivy | Latest |
| Git | Latest |
| GitHub | Cloud |

---

## Project Structure

```
NovaPay-CICD/
│
├── Jenkinsfile
├── namespace.yaml
│
└── app/
    └── novapay/
        ├── Dockerfile
        ├── Jenkinsfile
        ├── pom.xml
        ├── mvnw
        ├── mvnw.cmd
        ├── src/
        └── kubernetes/
            ├── blue/
            ├── green/
            ├── canary/
            └── service/
```

---

## CI/CD Pipeline

The Jenkins pipeline performs the following stages:

1. Source Checkout
2. Build
3. Unit Testing
4. SonarQube (Placeholder)
5. Dependency Scan
6. Docker Image Build
7. Trivy Container Scan
8. Docker Hub Push
9. Kubernetes Deployment
10. Deployment Verification
11. Rollback on Failure

---

## Docker Image

Docker Hub Repository

```
saikiran9068/novapay
```

Latest Image

```
saikiran9068/novapay:latest
```

---

## Kubernetes Deployment

Namespace

```
novapay
```

Deployments

- Blue Deployment
- Green Deployment
- Canary Deployment

Service

```
NodePort
```

---

## REST APIs

### Health Check

```
GET /api/payment/health
```

Response

```
UP
```

---

### Payment API

```
POST /api/payment/process
```

Response

```
Payment Successful
```

---

## Build Commands

Build Project

```bash
./mvnw clean package
```

Run Tests

```bash
./mvnw test
```

Build Docker Image

```bash
docker build -t saikiran9068/novapay:latest .
```

Push Docker Image

```bash
docker push saikiran9068/novapay:latest
```

---

## Kubernetes Commands

Create Namespace

```bash
kubectl apply -f namespace.yaml
```

Deploy Blue Version

```bash
kubectl apply -f app/novapay/kubernetes/blue/deployment.yaml
```

Deploy Service

```bash
kubectl apply -f app/novapay/kubernetes/service/service.yaml
```

Check Pods

```bash
kubectl get pods -n novapay
```

Check Service

```bash
kubectl get svc -n novapay
```

---

## Security

- Trivy Container Scanning
- Jenkins Credentials Management
- Kubernetes Namespace Isolation
- Docker Hub Authentication

---

## Features

- Automated CI/CD Pipeline
- Docker Containerization
- Kubernetes Deployment
- Blue-Green Deployment
- Canary Deployment
- Automated Rollback
- Container Security Scan
- Deployment Verification

---

## Screenshots Included

- Jenkins Successful Pipeline
- Docker Build
- Trivy Scan
- Docker Hub Repository
- Kubernetes Pods
- Kubernetes Services
- Kubernetes Deployments
- Application Running
- GitHub Repository

---

## Repository

GitHub Repository

```
https://github.com/saikiran9879/NovaPay-CICD
```

---

## Author

**Saikiran Patel**

DevOps Engineer

---

## License

This project is developed for educational and assessment purposes.
