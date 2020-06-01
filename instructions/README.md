- [LOGIN CREDENTIALS](login-information.md)  


## HOW TO START HORTONWORKS ENV?
- make sure that docker installed on your computer
- navigate to the folder `hdp_docker-deploy-scripts`
- execute script `hdp_docker-deploy-scripts`
```shell script
  sh hdp_docker-deploy-scripts
```

- connect into the docker image
```shell script
docker exec -it sandbox-hdp /bin/bash
``` 

- change the admin password in image
```shell script
ambari-admin-password-reset
``` 
