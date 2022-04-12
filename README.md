[aiacademy]: https://goai.co.kr "AIA aiacademy"

# ✌️Shopping mall Mini Project ✌️

[자바기반 Web Platform Service(RestFul Server) 구축 Web, App 개발자 양성과정][aiacademy]를 들으며  
11번의 Refactoring을 통해 Spring MVC Model2 Architecture 기반으로 제작한 Shopping mall 입니다. 

## Overview

### Model2MVCShop  
- 회원 가입 (중복체크)
- 회원 CRU
- 상품 CRUD
- HTTP multipart/form-data을 사용하여 file upload,  
- Ajax를 활용하여 무한스크롤 추가,   
- Ajax를 활용하여 회원가입 유무 체크,  
- 상품 배송 수락, 배송 정보 확인
- 상품 수량 구매
- 장바구니 기능 구현


### Update  history
- [REFACTORING 01](https://github.com/ohchangyeol/mini-project/tree/main/01.Model2MVCShop(stu))  
    Spring framework를 사용하지않고 dispatcherservlet, resolver, mapping, connection을 직접 구현하여 동작 방식의 이해하여 제작

- [REFACTORING 02](https://github.com/ohchangyeol/mini-project/tree/main/02.Model2MVCShop(Refactor%20%26%20Page%20Navigation))  
    Page **POJO**를 통해 Page Navigation 기능 추가

- [REFACTORING 03](https://github.com/ohchangyeol/mini-project/tree/main/03.Model2MVCShop(EL%2CJSTL))  
    Java server Page에 자바형식의 코드를 간편하게 사용할 수 있는 **EL**과,  
    태그처럼 연산이나 조건문이나 반복문인 if문, for문을 편하게 처리할 수 있는 **JSTL**로 업데이트

- [REFACTORING 04](https://github.com/ohchangyeol/mini-project/tree/main/04.Model2MVCShop(Business%20Logic%2CMyBatis%20Spring))  
    **Business Logic**에 복잡한 JDBC코드를 걷어내며 깔끔한 소스코드로 관리하고  
    직접 Beans가 RDBMS에 접근하는 형태보다는 dbcp와 Mybatis를 활용하여 RDBMS에 접근하고 SQL을 관리

- [REFACTORING 05](https://github.com/ohchangyeol/mini-project/tree/main/05.Model2MVCShop(AOP%2CTransaction))  
    **Business Logic**에 AOP를 이용한 Transaction을 사용하여 데이터 처리하는 과정에 오류가 발생할 경우 Rollback
- [REFACTORING 06](https://github.com/ohchangyeol/mini-project/tree/main/06.Model2MVCShop(Presentation%2BBusinessLogic))  
   refactoring 04, 05번을 통해 구현한 Business logic 부분을  controller를 만들어 annotation Mapping을 사용해 Presentation logic이랑 연결

- [REFACTORING 07](https://github.com/ohchangyeol/mini-project/tree/main/07.Model2MVCShop(URI%2Cpattern))  
    url pattern을 적용    

- [REFACTORING 08-Client](https://github.com/ohchangyeol/mini-project/tree/main/08.Model2MVCShop(RestFul%20Client))  
    Web을 platform하여 httpcomponents를 사용해 url 테스트  
    하고 data json simple과 jackson을 사용해 json 형식으로 변환 테스트. 

- [REFACTORING 08-Server](https://github.com/ohchangyeol/mini-project/tree/main/08.Model2MVCShop(RestFul%20Server))  
    Model과 View를 전송하는 방식이 아닌 RestController를 사용하여 Model(json) 전송  

- [REFACTORING 09](https://github.com/ohchangyeol/mini-project/tree/main/09.Model2MVCShop(jQuery))  
    정적인 JSP을 javascript(jQuery)를 활용해 동적으로 수정

- [REFACTORING 10](https://github.com/ohchangyeol/mini-project/tree/main/10.Model2MVCShop(Ajax))  
    자바스크립트(Ajax)를 통해서 서버에 데이터를 비동기 방식으로 요청하여 페이지 리로드 할 필요 없이 필요한 data를 추출

- [REFACTORING 11](https://github.com/ohchangyeol/mini-project/tree/main/11.Model2MVCShop)  
    css framework를 사용하여 Presentation 디자인 업데이트

- [REFACTORING 12](https://github.com/ohchangyeol/mini-project/tree/main/12.Model2MVCShop(shopping%20basket))  
    상품 수량 등록, 수정 기능 추가  
    상품 수량 구매 기능 추가  
    장바구니 등록, 부분 삭제, 일괄삭제, 구매 기능 추가

- [REFACTORING 13](https://github.com/ohchangyeol/mini-project/tree/main/13.Model2MVCShop(API)) *구현중*  
    다음 주소 API 추가  
    결제 API 추가 