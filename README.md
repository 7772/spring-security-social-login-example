# 스프링 시큐리티 Social login example

## Specifications

- Springboot 2.6.0
- Spring core 5.3.13
- Spring security 5.6.0
- Spring starter oauth2 client 2.6.0
- Spring data jpa 2.6.0

## 실행 방법

1. `application-oauth.yml` 생성

`application-oauth-example.yml` 파일을 복사하여 `application-oauth.yml` 을 만든다.

```
$ cp src/main/resources/application-oauth-example.yml src/main/resources/application-oauth.yml
```

Goole, Naver 개발자 센터에서 `client-id`, `client-secret` 을 발급받고, `application-oauth.yml` 에 작성한다.

```
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: {YOUR_CLIENT_ID}
            client-secret: {YOUR_CLIENT_SECRET}
            ...
          naver:
            client-id: {YOUR_CLIENT_ID}
            client-secret: {YOUR_CLIENT_SECRET}
            
            ...
```

`application.yml` 에 포함시킬 프로파일을 `oauth` 로 변경한다.

```
// AS-IS
spring:
  profiles:
#    include: oauth
    include: oauth-example
    
// TO-BE
spring:
  profiles:
    include: oauth
```

2. 프로젝트 실행

> -- Local DB 생성 및 flyway migrate 과정은 생략함 --

```
$ ./gradlew bootRun
```

## References

https://velog.io/@swchoi0329/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%EC%99%80-OAuth-2.0%EC%9C%BC%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84
