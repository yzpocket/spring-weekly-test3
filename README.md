# Spring Test3 repository
Spring 프레임워크를 공부하고 주간 테스트를 기록하는 공간입니다.

## 🖥️ 스터디 저장소 소개
* 문제를 통한 요구사항 실습
* 코드 수정 및 기능 구현을 통해 부족한 부분을 체크하는 테스트

## 🕰️ 개발 기간
* 23.09.14
  
## 🧑‍🤝‍🧑 맴버구성
- 김인용

## ⚙️ 개발 환경
- **MainLanguage** : `Java - JDK 17`
- **IDE** : `IntelliJ IDEA Ultimate`
- **Framework** : `Spring Boot`
- **Database** : `MySQL`
- **SERVER** : `Spring inner server(not published)`
- **TEST** : `POSTMAN`
- **OS** : `MacOS`

## 👋🏻 Contact
- **Email** : citefred@yzpocket.com
- **Blog** : https://www.citefred.com

## ⚠️ 주의
#### 추적 예외
* src/main/resources/application.properties 파일은 DB 접속 정보가 있어 추적이 제외되어 있습니다.
* MySQL을 연결 한 뒤 'market' 이름의 DATABASE를 생성해 주셔야 합니다.
```
create database market;
```
* 테스트를 진행 하시려면 위 경로 src/main/resources/ 에 파일(application.properties)을 생성해주세요.
  - 다음과 코드를 입력해주세요 < ... > 부분을 작성해주셔야 합니다. "<", ">" 괄호 제거해주세요.
  - ex) spring.datasource.username=root
```
#JDBC
spring.datasource.url=jdbc:mysql://localhost:3306/market
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#JPA
spring.jpa.hibernate.ddl-auto=update
## Options : create, create-drop, validate, none
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
```

## 📕 시험문제

<details>
<summary> #시험 문제 </summary>

# [항해99] 주특기 3주차 테스트(Spring)

<aside>
💡 **Goal**

---

중고거래 사이트인 항해마켓을 만들려 한다. 

판매자는 더 이상 사용하지 않는 물건을 등록하고, 구매자는 등록한 물건의 리스트와 상세설명을 조회할 수 있도록 사이트를 만들어보자.

</aside>

<aside>
📌 **features : 구현해야 할 기능이에요.**

</aside>

- 게시글 목록 DB는 다음과 같이 설계되어있어요.
- 테이블명 : Item
    - id : 게시글 번호 (DB 인덱스)
    - title (String) :  게시글 제목
    - content (String) : 게시글 내용
    - price(int) : 가격
    - username : 작성자
    
- 구현해야하는 API는 다섯개입니다.
    
    [API 설계](https://www.notion.so/146e1a34cf2f4ce88be1aea6cbae72d9?pvs=21)
  <br>
    ![image](https://github.com/yzpocket/spring-weekly-test3/assets/67217259/baa13125-c3e8-4acc-9f7c-506e2c127b05)

    - 판매 게시글을 작성하는 API
    - 판매 게시글을 상세 조회하는 API
    - 판매 게시글을 전체 조회하는 API
    - 판매 게시글을 수정하는 API
    - 판매 게시글을 삭제하는 API

<aside>
⚠️ **주의사항**

</aside>

- **IntelliJ로 구동시켜보고, postman으로 테스트를 진행해 보세요.**
게시글 작성을 3개 해보고, 전체조회를 해보세요!
- 모든 Entity는 그대로 반환하지 않고 **생성자를 사용하여 DTO로 변환한 후 반환**하세요!
    - Dto를 반환하지 않을 시 감점

</details>

## ✅ POSTMAN - TEST 결과
### API Specification Published by POSTMAN - https://documenter.getpostman.com/view/29343996/2s9YC4WDUG
#### 판매 게시글 작성 (CREATE-POST)
![image](https://github.com/yzpocket/spring-weekly-test3/assets/67217259/2bf5fe1e-e2ca-4c35-9c6e-28065b43a430)
#### 판매 게시글 상세 조회 (READ-GET)
![image](https://github.com/yzpocket/spring-weekly-test3/assets/67217259/7f4e2b79-c375-46b2-aa20-47742c419fcf)
#### 판매 게시글 전체 리스트 조회 (READ-GET)
![image](https://github.com/yzpocket/spring-weekly-test3/assets/67217259/875b0fe5-e750-49fc-bc25-d2219ea78df7)
#### 판매 게시글 수정 (UPDATE-PUT)
![image](https://github.com/yzpocket/spring-weekly-test3/assets/67217259/e3e8b233-7e6f-40a5-8309-820084f26317)
#### 판매 게시글 삭제 (DELETE-DELETE)
![image](https://github.com/yzpocket/spring-weekly-test3/assets/67217259/d54e78c3-da9f-4747-97f6-f4f152b0bf48)
