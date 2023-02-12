pipeline {
	agent any
	environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
	}
	stages {
		stage('Build'){
            steps {
				echo 'gradle build...'
				echo "docker hub creds $DOCKERHUB_CREDENTIALS"
                sh 'gradle clean build -Pbuildno=$BUILD_NUMBER'
            }
        }
		stage('Docker build') {
			steps {
				sh 'docker build . -t pipebuild:${BUILD_NUMBER} --build-arg BUILD_NUMBER=${BUILD_NUMBER}'
			}
		}
		stage('docker login') {
			steps{
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}
		stage('docker push') {
			steps {
				sh 'docker image tag pipebuild:${BUILD_NUMBER} ghodkenikhil/pipebuild:latest'
				sh 'docker push ghodkenikhil/pipebuild:latest'
			}
		}
		stage('k8s deployment') {
			steps {
				sh 'kubectl apply -f k8s/k8s-configs.yml'
			}
		}
	}
	post {
		always {
			sh 'docker logout'
		}
	}
}
