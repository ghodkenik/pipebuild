pipeline {
	agent { 
		docker.withTool('Docker') {
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
	}	
}
