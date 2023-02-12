pipeline {
	agent any
	stages {
		stage('Build'){
            steps {
				echo 'gradle build...'
                sh 'gradle clean build'
            }
        }
		stage('Docker build') {
			steps {
				sh 'docker build . -t pipebuild:${BUILD_NUMBER}'
			}
		}
	}	
}
