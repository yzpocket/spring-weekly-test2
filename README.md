# Spring Test repository
Spring 프레임워크를 공부하고 주간 테스트를 기록하는 공간입니다.

## 🖥️ 스터디 저장소 소개
* 문제를 통한 요구사항 실습
* 코드 수정 및 기능 구현을 통해 부족한 부분을 체크하는 테스트

## 🕰️ 개발 기간
* 23.09.07
  
### 🧑‍🤝‍🧑 맴버구성
- 김인용

### ⚙️ 개발 환경
- **MainLanguage** : `Java` - JDK 17
- **IDE** : IntelliJ IDEA Ultimate
- **Framework** : Spring Boot
- **Database** : H2
- **SERVER** : Spring inner server(not published)
- **OS** : MacOS

### 👋🏻 Contact
- **Email** : citefred@yzpocket.com
- **Blog** : https://www.citefred.com

# Spring 시험문제

<details>
<summary> #시험 문제 </summary>

스파르타에서 서점 서비스를 계획하고 있습니다. 서울지점, 부산지점을 운영할 예정이고 각 지점에서의 회원관리, 책 관리가 필요합니다. 아래의 요구사항에 따라 서비스를 완성시켜 주세요.

## 시험 문제 확인 및 실행 방법

- 아래 파일을 받아, 인텔리제이로 실행합니다.

  [jpa_relation_test.zip](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff754f5c-aef9-4fde-a2c5-6367cc91b5d1/jpa_relation_test.zip)

- SDK 설정을 해줍니다.
    - SDK 설정 방법
        1. 처음 프로젝트를 실행 하시면 우측 하단에 다음과 같은 알림이 뜹니다. Load버튼을 눌러주세요

           ![sdk3.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/47ab6506-c338-48bb-ad31-76f9291477c8/sdk3.png)

        2. 프로젝트 내부 클래스를 누르시면 우측 상단에 sdk를 세팅하는 버튼이 뜹니다. setup sdk를 누르신 후 17버전을 선택해 주세요.

           ![step2.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1df582f2-7c0f-456d-81cb-19c43dae7b14/step2.png)

        3. 좌측 상단, file - settings - build, execution, deployment - build tools - gradle 탭에서 아래와 같이 gradle jvm을 변경합니다. 이전에 선택하셨던 버전과 같은 버전의 sdk를 선택하셔야 합니다.

           ![step3.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9f60fcd5-edb2-4e3a-8e83-05bac330e806/step3.png)

- 아래와 같은 설정을 이용하여 H2 db에 접근 가능합니다.
    - 주소창에 `````````http://localhost:8080/h2-console` 를 입력합니다.
    - 아래와 같은 설정을 적용하고 connect 버튼을 누릅니다.

  ![스크린샷 2023-05-29 오후 8.52.36.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/228d288f-6277-4c15-81df-49e0b6ed8eb9/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-05-29_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_8.52.36.png)

- postman 대신 인텔리제이의 .http를 이용하여 http 요청을 하시면 됩니다.
  .Http 사용 방법은 다음과 같습니다.
    - resources - http 패키지의 Test.http를 열어줍니다.
    - 원하시는 요청 실행을 누르시면 해당 API로 http 요청을 보냅니다.

## 시험 문제 내용

### 구현 문제

1. 회원 테이블이 완성되어 있지 않습니다. .http 파일의 `Q1. 회원가입 API`에 따라 테이블을 작성해 주세요. `entity 패키지의 member 클래스를 완성해 주세요.`
    - 예상 return값 (4번문제 풀기 전)

      `.http를 확인해 주세요`

        ```json
        // A1. Response
        
        [
          {
            "id": 1,
            "email": "sparta@sparta.com",
            "password": "4321",
            "address": "부산시",
            "phoneNumber": "01012341234",
            "nickname": "스파르타",
            "bookStore": null
          },
          {
            "id": 2,
            "email": "hanghae99@sparta.com",
            "password": "1234",
            "address": "서울시",
            "phoneNumber": "01012345678",
            "nickname": "르탄이",
            "bookStore": null
          }
        ]
        ```


    Member 클래스 코드를 제출해 주세요.

2. 서점에 등록된 책의 가격과 재고를 수정해야 합니다. .http 파일의 `Q2. *서점에 책 등록된 책 수량 수정 API*`를 완성해 주세요.
   `service 패키지, TestService의 updateBook 메서드를 완성해 주세요.`
    - 예상 return값

      `.http를 확인해 주세요.`

        ```json
        // A2. Response
        
        [
          {
            "id": 1,
            "title": "자바의 정석 3판",
            "author": "남궁성",
            "price": 10000,
            "stock": 100,
            "bookStore": {
              "name": "스파르타 서울"
            }
          }
        ]
        ```


    updateBook 메서드 코드, BookRepository, Book 클래스를 제출해 주세요.

3. “자바의정석”책을 서울점에서 부산점으로 옮기려고 합니다. 현재 만들어진 *`Q3. 부산점에 자바의 정석 책 등록 API`*로는 어째서인지 정보가 수정되지 않습니다. 올바르게 작동하도록 수정해 주세요.
   `service 패키지, TestService의 transferBook 메서드를 완성해 주세요.`
    - 예상 return값

      `.http를 확인해 주세요.`

        ```json
        // A3. Response
        
        [
          {
            "id": 1,
            "title": "자바의 정석 3판",
            "author": "남궁성",
            "price": 10000,
            "stock": 100,
            "bookStore": {
              "name": "스파르타 부산"
            }
          },
          {
            "id": 2,
            "title": "자바 ORM 표준 JPA 프로그래밍",
            "author": "김영한",
            "price": 20000,
            "stock": 4,
            "bookStore": {
              "name": "스파르타 부산"
            }
          }
        ]
        ```


    transferBook 메서드 코드, Book 클래스 코드를 제출해 주세요.

4. 회원 테이블과 책 테이블간 다대다 매핑이 되어 있습니다. Purchase 테이블을 중간 테이블로 설정하여 1:N, M:1 의 관계로 바꿔 주세요.
   `entity 패키지의 purchase 클래스, 연관된 클래스들을 수정해 주세요.`
    - 예상 return값

      [`http://localhost:8080/h2-console](http://localhost:8080/h2-console) 를 확인해 주세요.`

      ![스크린샷 2023-05-30 오후 5.20.01.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/74f17e72-0fce-48ac-8670-699a654a6e1e/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-05-30_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_5.20.01.png)


    **Purchase, Book, Member 클래스 코드를 제출해 주세요.**

5. 회원 테이블에 서점 테이블 FK 컬럼명을 Sparta_Store_Id로 바꿔주세요. 단, Member 클래스와 BookStore 클래스를 변경시키지 않아야 합니다.
   `entity 패키지의 member클래스를 완성해 주세요.`
    - 예상 return값

      [`http://localhost:8080/h2-console](http://localhost:8080/h2-console) 를 확인해 주세요.`

      ![스크린샷 2023-05-30 오후 5.30.36.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a726f2f2-5d6c-4844-8a75-8365c2d3145f/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-05-30_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_5.30.36.png)


    **Member 클래스 코드를 제출해 주세요.**


### 서술형 문제

1. JPA에서, 기존의 정보를 수정하는 기능 작성 시 save 메서드가 필요 없는 이유와 동작 원리에 대해 서술해 주세요.

</details>
