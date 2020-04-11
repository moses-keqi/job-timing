# job-timing


 > mvn -DskipTests=true clean package docker:build
 
 > docker build -t job-timing .
  
  
 > docker run -d -p 8220:8220 --name job-timing job-timing
   
   
   
 

   
 > docker exec -it e021811057ce /bin/bash
   
   
 > #删除无用
 > docker system prune

