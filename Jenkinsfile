node {
  def project = 'insurance-policy'
  def appName = 'insurance-policy'
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
               

        sh("sed -i.bak 's#192.168.99.100:5000/insurance-policy/insurance-policy#${imageTag}#' ./helm/Insurance-policy/values.yaml")
         sh ("pwd")
        sh("/usr/local/bin/helm upgrade --install Insurance-policy ./helm/Insurance-policy --kubeconfig /var/lib/jenkins/workspace/config")
    
      
        echo 'To access your environment run `kubectl proxy`'
        echo "Then access your service via http://localhost:8001/api/v1/proxy/namespaces/${env.BRANCH_NAME}/services/${feSvcName}:80/"
  }
}
