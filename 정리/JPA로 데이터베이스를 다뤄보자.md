## JPA로 데이터베이스를 다뤄보자

**현재 운영을 진행하면서 MyBatis를 이용해 데이터베이스 쿼리를 작성한다.**

**즉, 실제 개발하는 시간보다 SQL을 다루는 시간이 더 많게 느껴진다는 것이다. **

**해당 문제는 객체지향 프로그래밍의 걸림돌이 된다.. 객체 모델링 보다는 테이블 모델링에 집중하게 되고...**



이를 해결하기 위해 즉,  관계형 데이터베이스를 이용해서 객체지향 프로그래밍을 할 수 있는 **JPA(자바 표준 ORM)이 나타나게 된다.**



**참고**

ibatis와 mybatis는 ORM이 아니고, SQL Mapper이다.

ORM은 **객체를 매핑하는 것**이고, SQL Mapper는 **쿼리를 매핑한다는 것**에 차이가 있다.



### JPA란,

현대 웹 애플리케이션에서는 관계형 데이터베이스가 필수적이다. Oracle, MySQL, MSSQL 등을 쓰지 않는 웹 애플리케이션이 거의 없으므로 **객체를 관계형 데이터베이스에서 관리하는 것**은 무엇보다 중요하다.

이 처럼, 관계형 데이터베이스가 중심이 되면서 모든 코드는 **SQL의 중심**이 되어 간다.

그로인해 개발자들은 CRUD SQL을 반복적으로 사용해야 한다.

이런 반복 작업에서 가장 큰 문제는 **패러다임 불일치**이다.



**패러다임 불일치**

- 관계형 데이터베이스는 어떻게 데이터를 저장할지 초점이 맞춰진 기술이다.
- 객체지향 프로그래밍 언어는 메시지를 기반으로 기능과 속성을 관리하는 기술이다.
- **결과적으로 관계형 데이터베이스와 객체지향 프로그래밍 언어는 사상이 다르므로, 같은 목표를 가질 수가 없는것이다.**



**객체 프로그래밍 언어의 소스를 확인해보자**

```java
User user = findUser();
Group group = user.getGroup();
```

**User(자식)와 Group(부모)은 누가봐도 부모-자식 관계임을 알 수 있다. -> User 본인이 속한 Group을 가져온다.**



**데이터베이스를 추가한 소스를 확인해보자**

```java
USer user = userDao.findUser();
Group group = groupDao.findGroup(user.getGroupId());
```

User 따로, Group 따로 조회하게 되는 현상을 볼 수 있다.

**즉, 상속 혹은 1:N 등의 다양한 객체 모델링을 데이터베이스로는 구현이 불가능하다는 것이다.**



이런 문제를 해결하기 위해 JPA가 나타났다.

**JPA는 관계형 데이터베이스와 객체지향 프로그래밍 언어을 중간에서 패러다임을 일치시켜준다**

**결과적으로, 개발자는 객체지향적으로 프로그래밍하고, JPA가 관계형 데이터베이스에 맞게 SQL을 대신 생성하여 실행하는 것이다.**

객체 중심으로 개발이 되므로 대규모 트래픽과 데이터를 가진 서비스에서는 JPA를 표준 기술로 자리 잡고 있다.



**Spring Data JPA**

인터페이스인 JPA를 사용하기 위해서는 구현체가 필요하나 Spring에서는 구현체를 직접 다루지 않는다.

즉, **Spring Data JPA**라는 모듈을 이용하여 JPA 기술을 다룬다.

JPA와 Spring Data JPA의 관계는 **JPA < Hibernate < Spring Data JPA**로 볼 수 있다. 

사실 Hibernate와 Spring Data JPA는 큰 차이가 없음에도 불구하고 Spring Data JPA가 나타난 이유는 두가지가 있다.

- 구현체 교체의 용이성
  - Hibernate 외에 다른 구현체로 쉽게 교체하기 위함이다.
  - Spring Data JPA를 사용하면 새로운 구현체를 쉽게 교체할 수 있다. **Why?** Spring Data JPA 내부에서 구현체 매핑을 지원해주기 때문인다.
- 저장소 교체의 용이성
  - 관계형 데이터베이스 외에 다른 저장소로 쉽게 교체하기 위함이다.
  - Spring Data JPA에서 Spring Data MongoDB로 교체가 필요하다면 개발자는 Spring Data JPA에서 Spring Data MongoDB로 의존성만 교체하면 된다.

이것이 가능한 이유는 Spring Data의 하위 프로젝트는 기본적으로 **CRUD 인터페이스가 같기** 때문이다.

즉, Spring Data의 하위 프로젝트들은 save(), findAll(), findOne() 등의 인터페이스를 갖고 있다. 그로인해 저장소가 바뀌어도 인터페이스의 구현 메소드가 동일하므로 기능 변경은 없는 것이다.



**실무에서는 JPA**

JPA를 잘 쓰려면 **객체지향 프로그래밍과 관계형 데이터베이스**를 둘 다 이해해야 한다. 

JPA의 장점

- CRUD를 직접 작성할 필요가 없어진다.
- 부모-자식 관계 표현, 1:N 관계 표현, 상태와 행위를 한곳에서 관리하는 등의 객체지향 프로그래밍을 쉽게 할 수 있다.



### 프로젝트에 Spring Data JPA 적용

**build.gradle에 추가**

```java
dependencies {
	...
        
    compile('org.springframework.boot:spring-boot-starter-data-jpa')
    compile('com.h2database:h2')
	
    ...
}
```

**spring-boot-starter-data-jpa**

- 스프링 부트용 Spring Data JPA 추상화 라이브러리이다.
- 스프링 부트 버전에 맞춰 자동으로 JPA 관련 라이브러리들의 버전을 관리해준다.



**h2**

- 인메모리 관계형 데이터베이스이다.
- 별도의 설치가 필요없이 프로젝트 의존성만으로 관리가 가능하다.
- 메모리에서 실행되기 때문에 애플리케이션을 재시작할 때마다 초기화된다는 점에서 테스트 용도로 많이 사용한다.
- 우리는 JPA 의 테스트, 로컬 환경에서의 구동에 사용할거다.



### JPA 기능을 사용해보자

**domain이라는 package를 추가하자**

![image](https://user-images.githubusercontent.com/40616436/73076111-cbcd4a00-3f00-11ea-9212-105aa00fe0c9.png)

여기서 도메인이란 게시글, 댓글, 회원, 정산, 결제 등의 소프트웨어에 대한 요구사항 혹은 문제 영역을 일컬어 하는 말이다.

MyBatis와 같은 쿼리 매퍼를 사용했다면 **dao** 패키지를 생각할 수 있는데, dao 패키지와는 조금 결이 다르다고 생각하면 된다.

기존에 xml에 쿼리를 담고, 클래스는 오직 쿼리의 결과만 담던 일들이 모두 **도메인 클래스**라고 불리는 곳에서 해결하면 되는 것이다.



*자! 이제 domain 패키지에 posts 패키지와 posts 클래스를 만들자*

```java
package com.mesung.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
```

**필수 어노테이션일 수록 클래스에 가깝게 추후 어노테이션 삭제를 진행할 때 쉽게 삭제가 가능하다**

**Posts 클래스는 실제 DB 테이블과 매칭될 클래스이며 Entity 클래스라고도 한다.**

**@Entity**

- 테이블과 링크될 클래스임을 나타낸다.
- 기본값으로 클래스의 카멜케이스 이름을 스네이크 네이밍으로 되어있는 테이블 이름과 매칭한다.
  - Ex. SalesManager => sales_manager TB

**@Id**

- 해당 테이블의 **PK 필드**를 나타낸다.

**@GeneratedValue**

- PK의 생성규칙을 나타낸다.
- 스프링 부트 2.0버전에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.



**PK 참고**

웬만하면 Entity의 PK는 Long 타입의 Auto_increment를 추천한다.

이유는, 주민번호와 같이 비즈니스상 유니크 키나 여러 키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 발생한다.

- FK를 맺을 때 다른 테이블에서 복합키 전부를 갖고 있거나 , 중간 테이블을 하나 더 둬야 하는 상황이 발생한다.
- 인덱스에 좋은 영향을 끼치지 못한다.
- 유니크한 조건이 변경될 경우 PK 전체를 수정해야하는 일이 발생한다.

주민번호, 복합키 등은 유니크 키로 별도로 추가하는 것을 추천한다.



**@Column**

- 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다.
- 근데 왜? 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
  - 예를들어, 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶을 때 사용할 수 있다.



**롬복에 관련된 어노테이션**

**@NoArgsConstructor**

- 기본 생성자 자동 추가

**@Getter**

- 클래스 내 모든 필드의 Getter 메소드를 자동생성

**@Builder**

- 해당 클래스의 **빌더 패턴** 클래스를 생성
- 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함

서비스 초기 구축 단계에서 테이블 설계가 빈번하게 변경되는데, 이 때 롬복의 어노테이션들은 코드 변경량을 최소화시켜 준다.



그런데 자세히 보면 이 Posts 클래스는 **Setter 메소드가 존재하지 않는다.**

자바빈 규약을 생각하면 **Getter/Setter는 무작정 생성**하는 경우가 있는데, 이렇게 구현하면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 없다.

그래서 **Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.** 대신, 해당 필드의 값을 변경하고 싶다면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야 한다.



**주문 취소라는 메소드를 만든다고 가정하에 소스를 살펴보자**

```java
//잘못 사용된 예
public class Order{
    public void setStatus(boolean status) {
        this.status = status;
    }
}

public void 주문서비스의_취소이벤트() {
    order.setStatus(false);
}

//올바른 사용 예
public class Order{
    public void cancelOrder() {
        this.status = false;
    }
}

public void 주문서비스의_취소이벤트() {
    order.cancelOrder();
}
```



**그렇다면 Setter가 없는 상황에서 우리는 어떻게 DB에 값을 채워넣을 수 있는 것인가?**

기복적인 구조로는 **생성자를 통해 최종값을 채운 후 DB에 삽입**하는 것이며, 값 변경이 필요한 경우에는 **해당 이벤트에 맞는 public 메소드를 호출**하여 변경하는 것을 전제로 한다.

현재 우리 소스에서는 생성자 대신 **@Builder**를 통해 제공되는 빌더 클래스를 사용하고 있는데, 생성하는 시점은 둘다 동일하다.

그러나, 생성자의 경우 지금 채워야할 필드가 무엇인지 명확히 지정할 수가 없다.

```java
public Example(String a, String b) {
    this.a = a;
    this.b = b;
}
```

만약 개발자가 `new Example(b, a)`처럼 a와 b의 위치를 변경시켜도 코드를 실행하기 전까지 문제를 찾을 수가 없다.

하지만 빌더를 사용하게 되면,

```java
Example.builder()
    .a(a)
    .b(b)
    .build();
```

다음과 같이 어느 필드에 어떤 값이 채워야할지를 명확하게 인지할 수 있는 것이다.



*자 이제 Post 클래스에서 Database를 접근하게 해줄 JpaRepository를 생성하자*

해당 Repository는 보통 ibatis나 MyBatis 등에서 Dao라고 불리는 DB Layer 접근자이다. **JPA에서는 Repository라고 부르며 인터페이스로 생성한다.**

단순히 인터페이스 생성 후 JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.

기본적으로 @Repository를 추가할 필요도 없다. 주의할 것은 Entity 클래스와 기본 Entity Repository는 함께 위치(같은 패키지)해야한다. **Entity 클래스는 기본 Repository 없이는 제대로된 역할을 수행할 수가 없다.**



### Spring Data JPA 테스트 코드 작성하기

PostsRepositoryTest를 작성해보자

```java
package com.mesung.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void boardList() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("myhs2002@gmail.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
```

 **@After**

- Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정한 것이다.
- 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용한다.
- 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아있어 다음 테스트 실행 시 실패할 수도 있다.

**postsRepository.save**

- 테이블 posts에 insert/update 쿼리를 실행한다.
- id 값이 있다면 update, 없다면 insert 쿼리가 실행된다.

**postsRepository.findAll**

- 테이블 posts에 있는 모든 데이터를 조회해오는 메소드이다.

별다른 설정없이 @SpringBootTest를 사용할 경우 **H2 데이터베이스**를 자동으로 실행해준다.



**실행되는 쿼리를 보고싶다면?**

로그확인을 위한 설정이 필요하다. resources/application.properties를 추가한다.

![image](https://user-images.githubusercontent.com/40616436/73116618-b3027a00-3f7c-11ea-8f49-0a1581b370bd.png)

```java
spring.jpa.show_sql=true
```

위 한줄로 실행되는 쿼리를 확인할 수 있다.

![image](https://user-images.githubusercontent.com/40616436/73116643-ff4dba00-3f7c-11ea-9278-23f21b58d91c.png)

![image](https://user-images.githubusercontent.com/40616436/73116650-1e4c4c00-3f7d-11ea-84f2-e2e4fdca0675.png)

실행된 쿼리가 잘 나타나는 것을 확인할 수 있다.

그런데, create table을 보면 **id bigint generated by default as identity**라는 옵션으로 생성되는 것을 볼 수 있는데 이는 H2의 쿼리 문법이 적용되었기 때문이다.

해당 쿼리를 MySQL 버전으로 변경해보자

```java
//application.properties
spring.jpa.properties.hibernate.dialect=ort.hibernate.dialect.MySQL5InnoDBDialect
```

![image](https://user-images.githubusercontent.com/40616436/73117427-de8b6180-3f88-11ea-8f5a-fb481a26b9bd.png)

MySQL 버전으로 쿼리가 작성된 것을 확인할 수 있다.