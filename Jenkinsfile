pipeline {
	agent { docker image 'gradle:7.6.0-jdk-alpine' }
	stages {
		stage('Build'){
            steps {
				echo 'gradle build...'
                sh 'gradle clean build'
            }
        }
		stage ('Docker build') {
			steps {
				sh 'docker build -t pipebuild:1.0 .'
			}
		}
	}	
}
