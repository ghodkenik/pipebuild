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
                caCertificate: "LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURCVENDQWUyZ0F3SUJBZ0lJSkIydGx3MzdHU0V3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TkRBeU1EZ3hOek14TlRaYUZ3MHpOREF5TURVeE56TTJOVFphTUJVeApFekFSQmdOVkJBTVRDbXQxWW1WeWJtVjBaWE13Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLCkFvSUJBUUMrTEJGZWRmUHFRZGFPdFU3TGZVcDVNankrWFhianhFcXYrSTRNaVkxTzllanQvZzJ5elRvWGlqTEYKWlNIRkloUis0aFBWOUJLRTVmUXJROUNwRENpUUZQaitFVlhCWFJHQk5PMlJlS0t4bHJldEpGcElYaVJQd0dhcAp0cU15Q1Vub0o3TUl1OE41aEtmNERGdjVya0dEdGJhU3o1c3l1RVZWMm1odER3Qi9haVBBOE5zQkRnZnRpcGVhClNFWUVSbkZ3WDBzSXlDcXNqeVdaeEpBa2Q1WTFvbmJOM2U1MUtBV1djeE1YYnpVSHg4NzBSSktleUJSS2dPWUYKTHk5eWFLR202bEg0TFdVS0h0UFgvOVoyZGgyYU9GbEhhVXBCOHFxR2hvTTc3RCtEM29mdGkwdGZsZEJPakNYbgo3dUZGdnNiUXhFVGRCSFRVdFloaVRHTjZLcDlqQWdNQkFBR2pXVEJYTUE0R0ExVWREd0VCL3dRRUF3SUNwREFQCkJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJTcTA2OVgxSmdnRzVsN2pvbUx0bE44c0NrZ1BqQVYKQmdOVkhSRUVEakFNZ2dwcmRXSmxjbTVsZEdWek1BMEdDU3FHU0liM0RRRUJDd1VBQTRJQkFRQVRlRFF0aXRiZwpCT1R0RHdaNkRDWVhNeHUxSW5CNlBTVjQ0QWZ1SDZuejN3QjNnNG1CV2R4Q3o2TStZZVl3VWtFSmdFc3o5VjU1CkNVbjh1dm4rT1FhekNQTlNTOFNYT2plaG0xSWRZQnRDNWZ2MUNzYkJxRlZxamZxOXdFYk81bHNVNGowNytvaUEKc2hreE9IMEZCNU9sL0VvNEpMR2RvTmVHRm9tZmk4OGZYbUZTZC9aY3lySjVjeGM5WWQ1ZmdZTUNCcWlXTURSawpRV0x0OFVqMEd1TkVUN0d6TldrV3kzeVFqRU1Tamt5QjdRVFRXcXpxNjVtaWV2NVdJRDBVa1RnZ2cxTlBEbGNWCkJhWWNKUXFnV1k3N3JVc3BiMXhiWGZOVVU4cnpSdklJTjdVWENoUDI0RksxZDJQNzhYWWE0VGsyWkozdUx1R1QKUFlicDZ5ZS9nVEE5Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K",
				// below serverurl is from minikube kubectl -- proxy --port=9999
				// this url can be changed to other k8s cluster by specifying API URL.
				// exposed API's can be accessed through http://localhost:9999/api/
                serverUrl: 'https://ec2-13-233-126-206.ap-south-1.compute.amazonaws.com:6443',
                contextName: 'kubernetes-admin@kubernetes',
                clusterName: 'kubernetes',
                namespace: 'default'
                ]) {
					sh 'helm dependency build k8s/helm/spring-app/'
					sh 'helm dependency build k8s/helm/nginx/'
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
