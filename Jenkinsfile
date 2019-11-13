pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Test') { 
            steps {
                sh 'mvn test' 
            }
            post {
                always {
                     junit "/report/*.xml"
                }
            }
        }
       
    }
}
