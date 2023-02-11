pipeline {
	agent { 
		docker {
			image 'gradle:7.6.0-jdk-alpine'
		}
	}
	stages {
		stage('Build'){
            steps {
				echo 'gradle build...'
                sh 'gradle clean build'
            }
        }
		agent any
		stage('Docker build') {
			sh 'docker build . -t pipebuild:2.0'
		}
	}	
}
