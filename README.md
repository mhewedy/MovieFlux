
# MovieFlux  
  
Trying to utilize the following:   
* Spring WebFlux (build reactive apps)  
* [Exposed DSL](https://github.com/JetBrains/Exposed) + [R2DBC](https://github.com/r2dbc/r2dbc-spi)  
* Kotlin  
  
## Usage:  
### Add user

```bash
curl -X POST -H 'Content-Type: application/json' http://localhost:8080/users -d \
        '{"firstName": "Mohammad", "lastName": "hewedy", "birthDay": "1985-01-01"}'
```  

### List users
  
```bash
curl http://localhost:8080/users | jq  
```
  
## Links:  
  
* https://github.com/JetBrains/Exposed/issues/13  
* https://docs.spring.io/spring-data/r2dbc/docs/1.0.x/reference/html/