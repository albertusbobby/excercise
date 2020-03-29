# exercise
This microservices project for get people data and see historical disease

if failed run docker, you can run manually:

1. first run people project (master project and have spring datasource), run on people folder:
mvn spring-boot:run -Dmaven.test.skip=true
after that, you can see on swagger: http://localhost:8081/swagger-ui.html
2. second run history project, run on history folder:
mvn spring-boot:run -Dmaven.test.skip=true
after that, you can see on swagger: http://localhost:8082/swagger-ui.html
3. third run data project, run on data folder:
mvn spring-boot:run -Dmaven.test.skip=true
after that, you can see on swagger: http://localhost:8083/swagger-ui.html


https://medium.com/@tariqul.islam.rony/spring-boot-and-multi-stage-dockerized-image-with-mysql-in-docker-compose-part-3-2999b2bdf6aa