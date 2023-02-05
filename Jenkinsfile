pipeline {
	agent any
	stages {
		stage('Build'){
            		steps {
						echo 'gradlew build...'
                		sh './gradlew clean build'
            		}
        	}
	}	
}
