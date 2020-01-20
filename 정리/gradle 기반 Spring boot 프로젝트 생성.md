## gradle 기반 Spring boot 프로젝트 생성

### build.gradle 설정

```java
buildscript {
    ext {
        springBootVersion = '2.1.7.RELEASE'
    }
    //ext{}는 build.gradle에서 사용하는 전역변수를 설정하겠다라는 의미
    //의미 : springBootVersion을 2.1.7.RELEASE로 하겠다.
    
    repositories {
        mavenCentral()
        jcenter()
    }
    
    
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
    //spring-boot-gradle-plugin라는 스프링 부트 그레이들 플러그인의 2.1.7.RELEASE를 의존성으로 받겠다는 의미이다.
    //${springBootVersion}으로 사용해야지만, 각 라이브러리들의 버전 관리가 한 곳으로 집중되고, 버전 충돌 문제도 해결된다
    
}
//해당 프로젝트의 플러그인 의전성 관리를 위한 설정


apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'
//자바와 스프링 부트를 사용하기 위한 필수적인 플러그인들.

group 'com.jojoldu.book'
version '1.0.4-SNAPSHOT-'+new Date().format("yyyyMMddHHmmss")
sourceCompatibility = 1.8

repositories {
    mavenCentral()
    jcenter()
}
//repositories는 각종 의존성들을 어떤 원격 저장소에 받을지를 지정한다.
//기본적으로 mavenCentral()을 자주 사용하나, 최근에는 jcenter()를 많이 사용한다.
	//이유는 mavenCentral()는 라이브러리 업로드를 위해서 많은 설정이 필요한 반면, jcenter() 라이브러리 업로드를 간단하게 할 수 있기 때문이다.
	//또한, jcenter() 라이브러리를 업로드하면 mavenCentral에도 업로드가 가능하다.

dependencies {
    compile('org.springframework.boot:spring-boot-starter-web')
    testCompile('org.springframework.boot:spring-boot-starter-test')
}
//dependencies는 프로젝트 개발에 필요한 의존성들을 선언하는 곳이다.
```

