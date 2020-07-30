node {
  def project = 'neargrocery'
  def appName = 'usuario-service-cmd'
  def feSvcName = "${appName}-backend"
  def imageTag = "192.168.99.100:5000/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

  checkout scm

  stage 'Build image'
  sh("docker build -t ${imageTag} .")

  stage 'Run Go tests'
  //sh("docker run ${imageTag} go test")

  stage 'Push image to registry'
  sh("docker push ${imageTag}")

  stage "Deploy Application"
  switch (env.BRANCH_NAME) {
   
    // Roll out to canary environment
    case "canary":
        // Change deployed image in canary to the one we just built
        sh("sed -i.bak 's#192.168.99.100:5000/usuario-service:v1.4#${imageTag}#' ./yamls/canary/*.yaml")
        //sh("kubectl --namespace=production apply -f yamls/services/")
        sh("kubectl --namespace=production apply -f yamls/canary/")
        sh("echo http://`kubectl --namespace=production get service/${feSvcName} --output=json | jq -r '.status.loadBalancer.ingress[0].ip'` > ${feSvcName}")
        break

    // Roll out to production
    case "master":
        // Change deployed image in canary to the one we just built
        sh("sed -i.bak 's#192.168.99.100:5000/usuario-service:v1.4#${imageTag}#' ./yamls/production/*.yaml")
        //sh("kubectl --namespace=production apply -f yamls/services/")
        sh("kubectl --namespace=production apply -f yamls/production/")
        sh("echo http://`kubectl --namespace=production get service/${feSvcName} --output=json | jq -r '.status.loadBalancer.ingress[0].ip'` > ${feSvcName}")
        break

    // Roll out a dev environment
    default:
        // Create namespace if it doesn't exist
        //sh("kubectl get namespace ${env.BRANCH_NAME} || kubectl create ns ${env.BRANCH_NAME}")
        // Don't use public load balancing for development branches
        //sh("sed -i.bak 's#LoadBalancer#ClusterIP#' ./k8s/services/frontend.yaml")
               

        sh("sed -i.bak 's#192.168.99.100:5000/neargrocery/usuario-service-cmd#${imageTag}#' ./helm/usuario-service-cmd/values.yaml")
        //sh("kubectl --namespace=${env.BRANCH_NAME} apply -f yamls/services/")
    //withKubeConfig([credentialsId: 'jenkins-deployer', serverUrl: 'https://192.168.99.100:6443']) {
     //sh("kubectl version")
    //}
    
     // kubernetesDeploy(
       //             kubeconfigId: 'jenkins-deployer',
         //           configs: 'yamls/dev/*.yaml',
           //         enableConfigSubstitution: true
             //   )
    sh ("pwd")
      sh("/usr/local/bin/helm upgrade --install usuario-service-cmd ./helm/usuario-service-cmd --kubeconfig /var/lib/jenkins/workspace/config")
    
      
    //kubernetesDeploy(
      //              kubeconfigId: 'kubeconfig',
        //            configs: 'yamls/dev/*.yaml',
          //          enableConfigSubstitution: true
            //    )
        echo 'To access your environment run `kubectl proxy`'
        echo "Then access your service via http://localhost:8001/api/v1/proxy/namespaces/${env.BRANCH_NAME}/services/${feSvcName}:80/"
  }
}
