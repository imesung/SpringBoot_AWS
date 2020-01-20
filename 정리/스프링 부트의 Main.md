## 스프링 부트의 Main

### Main Application

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

**@SpringBootApplication**

- 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성이 모두 가능하다.
- 해당 애노테이션이 있는 부분부터 설정을 읽어가기 때문에, 해당 애노테이션이 있는 클래스는 항상 프로젝트의 최상단에 위치해야한다.

**SpringApplication.run()**

- run()으로 인해 Spring Boot의 내장 WAS를 실행한다.
- 이를 통해서 톰캣을 설치할 필요가 없고, 스프링 부트로 만들어진 Jar 파일로 실행이 가능하다.
- SpringBoot에서는 내장 WAS를 사용하기를 **권장**하고 있는데, 이유는 **언제 어디서든 같은 환경에서 SpringBoot를 배포할 수 있기 때문이다.**

