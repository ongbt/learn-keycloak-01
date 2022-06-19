## Start
### Docker:
- https://hub.docker.com/r/bitnami/keycloak/
- Download _docker_compose.yml_:
  - `curl -LO https://raw.githubusercontent.com/bitnami/bitnami-docker-keycloak/master/docker-compose.yml`
  - Modify _docker_compose.yml_: change "80:8080" to "8180:8080"
- Run `docker-compose up`
- Keycloak console at: http://localhost:8010 

### Setup test realm:
- Login as default admin account
    - user:bitnami
- Create realm: _SpringBootKeycloak_
    - Redirect URL: http://localhost:8081/*
    - Create role: user
    - Create user: user1:ASDqwe123!@#

### Run application
- Commandline `gradle bootRun`
- Go to _http://localhost:8081/posts_. 
  - You will be redirected to Keycloak's login page.
  - Login as _user1_
  - You will redirected back and given access
- Go to _http://localhost:8081/posts/admin_. 
  - You will be redirected to Keycloak's login page.
  - Login as _user1_ (which does not have the _admin_ role)
  - You will redirected to Forbidden (403) page
- Logout:
  - Go to: _http://localhost:8081/logout_
- For non-GET requests, you need to get the access token via the Keycloak REST API:
  - Go to: localhost:8180/realms/SpringBootKeycloak/protocol/openid-connect/token
  - See this [article](https://blog.devgenius.io/secure-your-spring-boot-application-using-keycloak-8c63e0530089) on how to get the access token
## Issues
- Issue: `"error": "RESTEASY003210: Could not find resource for full path: http://localhost:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token"`
    - See: https://stackoverflow.com/questions/70577004/keycloak-could-not-find-resource-for-full-path
- Issue: `error_description": "Account is not fully set up"`
    - https://stackoverflow.com/questions/42524153/keycloak-not-returning-access-token-if-update-password-action-selected
- Issue: `Exception: org.springframework.beans.factory.UnsatisfiedDependencyException. Message: Error creating bean with name 'securityConfig': Unsatisfied dependency expressed through field 'keycloakConfigResolver'; nested exception is org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with name 'securityConfig': Requested bean is currently in creation: Is there an unresolvable circular reference?`
    - https://github.com/eugenp/tutorials/commit/a9417007b4e3df11a3a5180621f4a3cc05f74c3b
- Issue: ` Failed to load URLs from http://localhost:8180/auth/realms/SpringBootKeycloak/.well-known/openid-configuration`
    - Remove _/auth_
    - See: https://stackoverflow.com/questions/70577004/keycloak-could-not-find-resource-for-full-path


## Ref:
- https://www.baeldung.com/spring-boot-keycloak
- https://gist.github.com/ThomasVitale/5544d276479d3895f4e8632720f5f92b
- https://blog.devgenius.io/secure-your-spring-boot-application-using-keycloak-8c63e0530089
