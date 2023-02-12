pipeline {
	agent any
	stages {
		stage('Build'){
            steps {
				echo 'gradle build...'
                sh 'gradle clean build -Pbuildno=$BUILD_NUMBER'
            }
        }
		stage('Docker build') {
			steps {
				sh 'docker build . -t pipebuild:${BUILD_NUMBER} --build-arg BUILD_NUMBER'
			}
		}
	}	
}
