pipeline {
	agent any
	stages {
		stage('Build'){
            steps {
				echo 'gradle build...'
                sh 'gradle clean build'
            }
        }
		stage ('Docker build') {
			steps {
				sh 'docker build -f ./Dockerfile -t pipebuild:1.0'
			}
		}
	}	
}
