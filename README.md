# tutoring-app

## 프로젝트 소개

Tutoring App은 학생과 튜터 간의 수강에 대한 처리를 위해 설계되었습니다. 강의 과정, 수강권, 수업, 사용자 등의 엔티티로 이루어져 있습니다.

### 엔티티 클래스 설명

### Course 클래스

Course 클래스는 강의 과정을 나타냅니다. 강의 과정은 다음과 같은 속성을 가집니다.

- 제목(title)
- 수업 타입(classType)
- 언어(language)
- 수업 횟수(lessonCount)
- 가격(price)
- 판매 시작일(saleStartDate)
- 판매 종료일(saleEndDate)
- 판매 종료 상태(isSaleEnded)

강의 과정은 여러 개의 Subscription(수강권)과 연관되어 있으며, 하나의 강의 과정에는 여러 개의 Lesson(수업)이 있을 수 있습니다.

### Lesson 클래스

Lesson 클래스는 강의 수업을 나타냅니다. 강의 수업은 다음과 같은 속성을 가집니다.

- 날짜(date)
- 수강 기간(duration)
- 강의 시작 시간(lessonStartTime)
- 수업 타입(classType)
- 수업 언어(languageType)
- 수업 진행 상태(isStarted, isFinished)

강의 수업은 하나의 Course(강의 과정)과 하나의 Member(튜터)와 연관되어 있으며, 여러 명의 Member(학생)이 참여할 수 있습니다.

### Member 클래스

Member 클래스는 사용자를 나타냅니다. 사용자는 학생과 튜터로 나눌 수 있으며, 각각의 사용자는 다음과 같은 속성을 가집니다.

- 회원 아이디(username)
- 비밀번호(password)
- 역할(role) - 학생,튜터,어드민

학생은 SubscriptionMember(수강권 사용 관리)와 연관되어 있으며, 튜터는 Course(강의 과정)와 Lesson(강의 수업)을 진행하며 수업료를 받을 수 있습니다.

### SpecialEvent 클래스

SpecialEvent 클래스는 특별 이벤트를 나타냅니다. 특별 이벤트는 다음과 같은 속성을 가집니다.

- 제목(title)
- 할인율(discount)

SpecialEvent는 SubscriptionMember(수강권 구매)와 연관될 수 있습니다.

### Subscription 클래스

Subscription 클래스는 수강권을 나타냅니다. 수강권은 다음과 같은 속성을 가집니다.

- 제목(subs_title)
- 사용 기한(exp_type)
- 사용 횟수(usage_count)
- 가격(price)
- 판매 종료 상태(isSalesEnded)

수강권은 하나의 Course(강의 과정)에 속하며, 여러 개의 SubscriptionMember(수강권 구매)와 연관될 수 있습니다.

### SubscriptionMember 클래스

SubscriptionMember 클래스는 수강권 사용자의 정보를 나타냅니다. 수강권 사용자는 다음과 같은 속성을 가집니다.

- 수강 횟수(usageCount)
- 만료일(expirationDate)
- 사용 가능 여부(isActive)
- 실제 구매 가격(salePrice)
- 사용 여부(isUsed)

수강권 사용자는 하나의 Subscription(수강권)과 하나의 Member(학생)와 연관되어 있으며, 적용 이벤트(specialEvent)를 통해 할인가를 적용시킬 수 있습니다.

## 사용한 도구 및 기술

- Spring Boot: 웹 애플리케이션 개발을 위한 프레임워크
- Spring Security: 사용자 인증 및 권한 관리를 위한 보안 프레임워크
- Spring WebSocket: 실시간 알림 기능을 구현하기 위한 WebSocket 지원
- Spring Data JPA(Hibernate): 객체와 데이터베이스 간의 매핑을 쉽게 처리하기 위해 사용
- Firebase: 알림 발송 및 푸시 알림 서비스
- Swagger: API 문서 자동화를 위한 도구

## 추가 설명

- 주어진 도메인 설명에 따라 연관관계를 설계하여 엔티티 간의 관계를 설정하였습니다.
- 더미 데이터를 자동으로 데이터베이스에 생성되도록 구현하였습니다.
- 프로젝트는 스프링 부트, 스프링 시큐리티, 스프링 웹 소켓, Firebase 및 Swagger를 활용하여 개발되었습니다.
- DDL은 JPA를 통해 코드로 설정했습니다.

## Swagger API 문서

- 프로젝트는 Swagger를 사용하여 API 문서를 자동화하였습니다.
- API 문서를 통해 프로젝트의 엔드포인트, 요청/응답 형식, 인증 등을 확인할 수 있습니다.
