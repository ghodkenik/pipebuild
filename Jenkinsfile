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
				sh 'docker push'
			}
		}
		stage('docker login') {
			steps{
				sh 'docker login -u '
			}
		}
		stage('docker push') {

		}
	}	
}
