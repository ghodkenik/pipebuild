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
				def springImage = docker.build("pipebuild:1.0")
			}
		}
	}	
}
