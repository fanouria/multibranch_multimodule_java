pipeline{
    agent any 
    tools{
        maven "maven-3.6.1"
    } 
    stages{
        stage("Main branch"){
            when {
                branch 'main'
            }
            stages{
                stage("Input message"){
                    input{
                        message "do you want to deploy?"
                        ok "Yes!"
                        parameters{
                            string(name:'OUTPUT', defaultValue:'1.4.0', description:"Enter a text")
                        }
                    }
                    steps{
                        echo "The output is: ${OUTPUT}"
                    }
                }
                stage("Deployment"){
                    steps{
                        echo "I deployed to production."
                    }
                }
            }
            post{
                success{
                    mail to:"fanouria.ath@gmail.com",
                    subject:"SUCCESSFUL BUILD: $BUILD_TAG",
                    body:"Link to JOB $BUILD_URL"
                }
                failure{
                    mail to:"fanouria.ath@gmail.com",
                    subject:"FAILURE BUILD: $BUILD_TAG",
                    body:"Link to JOB $BUILD_URL"
                }
            }
        }
        stage("Development branch"){
            when{
                branch 'development'
            }
            stages{
                stage("Package the application"){
                    steps{
                        bat "mvn package -Dmaven.test.skip=true"
                    }                    
                }

            }

        }
        stage("PR branch"){
            when{
                branch 'PR**'
            }
            stages{
                stage("Package the application"){
                    steps{
                        bat "mvn package -Dmaven.test.skip=true"
                    }                    
                }
                stage("Clean old mvn output"){
                    steps{
                        bat "mvn clean"
                    }
                }
                stage("Compile"){
                    steps{
                        bat "mvn clean compile"
                    }
                }
                stage("Testing"){
                    steps{
                        bat "mvn test"
                    }
                    post{
                        always{
                            junit '**/target/surefire-reports/*.xml'
                        }
                    }
                }   
            }             
        }      
        
    }

}