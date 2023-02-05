pipeline {
	agent any
	stages {
		stage('Build'){
            		steps {
						echo 'gradle build...'
                		sh './gradlew clean build'
            		}
        	}
	}	
}
