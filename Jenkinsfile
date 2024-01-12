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
		stage('Docker build nginx') {
			steps {
				sh 'docker build . -t nginx-custom:${BUILD_NUMBER} -f nginx/Dockerfile --build-arg BUILD_NUMBER=${BUILD_NUMBER}'
			}
		}
		stage('docker login') {
			steps{
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}
		stage('docker push') {
			steps {
				sh 'docker image tag pipebuild:${BUILD_NUMBER} ghodkenikhil/pipebuild:${BUILD_NUMBER}'
				sh 'docker push ghodkenikhil/pipebuild:${BUILD_NUMBER}'

				sh 'docker image tag nginx-custom:${BUILD_NUMBER} ghodkenikhil/nginx-custom:${BUILD_NUMBER}'
				sh 'docker push ghodkenikhil/nginx-custom:${BUILD_NUMBER}'
			}
		}
		stage('helm deployment') {
			steps {
    			withKubeConfig([credentialsId: 'jenkins',
                caCertificate: "",
                serverUrl: 'https://127.0.0.1:9999',
                contextName: 'kubernetes-admin@kubernetes',
                clusterName: 'kubernetes',
                namespace: 'default'
                ]) {
      				sh 'helm install 3tiernginx k8s/helm/nginx/ --set 3tiernginx.image.tag=${BUILD_NUMBER}'
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
