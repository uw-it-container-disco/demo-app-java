## Codefresh CI Status 
develop : 
[![Codefresh build status]( https://g.codefresh.io/api/badges/pipeline/maximede/uw-it-container-disco%2Fdemo-app-java%2Fdemo-app-java?branch=develop&key=eyJhbGciOiJIUzI1NiJ9.NWFlMWZjZTZmZGU0ODMwMDAxNzdiODlh.hfwPGkMx_oRvQfAtMboPqY3qXL38hCNl1OsWxd70BPY&type=cf-1)]( https://g.codefresh.io/repositories/uw-it-container-disco/demo-app-java/builds?filter=trigger:build;branch:develop;service:5afb659ee710ce0001057d6d~demo-app-java)

master : 
[![Codefresh build status]( https://g.codefresh.io/api/badges/pipeline/maximede/uw-it-container-disco%2Fdemo-app-java%2Fdemo-app-java?branch=master&key=eyJhbGciOiJIUzI1NiJ9.NWFlMWZjZTZmZGU0ODMwMDAxNzdiODlh.hfwPGkMx_oRvQfAtMboPqY3qXL38hCNl1OsWxd70BPY&type=cf-1)]( https://g.codefresh.io/repositories/uw-it-container-disco/demo-app-java/builds?filter=trigger:build;branch:master;service:5afb659ee710ce0001057d6d~demo-app-java)

# Demo app using java


## Build : 
`docker build -t demo-app-java .`


## Run without k8s:
`docker run -it --rm -p 8080:8080 demo-app-java`

# Deploy to a k8s cluster

You'll need the helm cli installed on your machine and tiller deployed on your k8s cluster (https://docs.helm.sh/using_helm/#quickstart)

( If using Docker for mac or minikube, Set the `--image-pull-policy` flag to `Never` to always use the local image, rather than pulling it from your Docker registry)

* You'll first need to build the docker image `docker build -t demo-app-java .`
* Then add the required spring secret : `kubectl create secret generic spring-security --from-literal=SPRING_SECURITY_USER_PASSWORD="pwd" `
* run `helm install --set service.type=NodePort --set image.pullPolicy=Never ./demo-app-java -n demo-app-java`
* find the port that has been assigned to the app by running `kubectl get svc`
* open a browser to http://localhost:$NODEPORT/hello

You can update the number of running pods by executing `kubectl scale --replicas=3 deployment/demo-app-java` or `helm upgrade demo-app-java --set replicaCount=5 --set service.type=NodePort --set image.pullPolicy=Never demo-app-java`

* purge the helm deployment by running `helm del --purge demo-app-java`

# CI Pipeline

![Pipeline flowchart](/docs/ci_pipeline_flowchart.png)
