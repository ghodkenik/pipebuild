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
				withGradle {
					sh 'gradle clean build -Pbuildno=$BUILD_NUMBER'
				}
            }
        }
		stage('Docker build') {
			steps {
				sh 'docker build . -t pipebuild:${BUILD_NUMBER} --build-arg BUILD_NUMBER=${BUILD_NUMBER}'
			}
		}
		stage('docker login') {
			steps{
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}
		stage('docker push') {
			steps {
				sh 'docker image tag pipebuild:${BUILD_NUMBER} ghodkenikhil/pipebuild:1.1'
				sh 'docker push ghodkenikhil/pipebuild:1.1'
			}
		}
		stage('helm deployment') {
			steps {
    			withKubeConfig([credentialsId: 'jenkins',
                caCertificate: "",
                serverUrl: 'https://172.16.152.10:6443',
                contextName: 'kubernetes-admin@kubernetes',
                clusterName: 'kubernetes',
                namespace: 'default'
                ]) {
      				sh 'helm install 3tiernginx k8s/helm/nginx/'
    			}
			}
		}
	}
	post {
		always {
			sh 'docker logout'
		}
	}
}
