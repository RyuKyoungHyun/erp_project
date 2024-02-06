# 백엔드 프로젝트 2

### 주제
- springBoot를 이용한 편의점 erp 프로그램 만들기

- - - -
### 사용기술
- Front : HTML / CSS / JavaScript / jQuery / Ajax
- Back : JAVA / springBoot / thymeleaf
- Tools : intellij / bootstrap / mySQL

- - - -
### 담당업무
- 발표 
- 품목관리 / 계약관리 / 계획관리

- - - -
### 기술서
- 다양한 품목들을 한 날짜를 기준으로 계획을 수립하기 위해 해당품목을 선택하고 수량을 입력한 뒤, 추가버튼을 통해 리스트에 추가해줍니다.
<img src="/readmeImg/planAdd.png" alt="skillScreen1" style="height: 400px; width:700px;"/>
- JavaScript를 통해서 배열속에 객체형식으로 키와 값을 설정해 넣어줍니다. 그리고 이것을 Controller로 보내기 위해 Ajax를 이용해 보내주고 보내준 뒤에는 새로고침으로 바뀐 값을 받을 수 있도록 합니다.
<img src="/readmeImg/planJavaScript.png" alt="skillScreen2" style="height: 400px; width:700px;"/>
- Ajax에서는 배열의 형태로 보내지고, Controller에서는 String 타입으로 받게 됩니다. 이 String 타입을 속의 객체들을 JSON형태로 쓰기 위해서 'JSONPaser'를 이용합니다.
- JSONPaser를 통해 JSONArray 타입을 바꿔주고 배열 속의 값들을 JSONObejct로 바꿔줍니다. 그리고 JSONObject 속에서 get메서드를 통해 키를 입력하여 값을 꺼내옵니다.
- 꺼내진 값들을 DB에 보낼 List로 재구성하여 for문을 통해 하나씩 넣어줍니다. saveAll에 List타입을 넣을시 순서대로 DB에 저장됩니다.
<img src="/readmeImg/planService.png" alt="skillScreen3" style="height: 400px; width:700px;"/>

- - - -
### 프로젝트 후기
- 시스템이 잡힌 곳에서 일을 해보지 못해 ERP 시스템에 대한 이해를 잡는 것이 초기에는 힘들었습니다. 업무 프로세스들을 어떻게 흘러가는가에 대해서 이해하기 위해 공부도 많이 하고 이를 어떻게 코드로 짜서 보여줄 것인지 고민이 정말 많았습니다.
- 프로세스들을 거치며 필수적인 것들을 팀원들과 함께 확인하고 서로 소통하며 프로그램에 대한 이해도가 높아지는 과정이 좋았다고 생각됩니다. 저번 프로젝트에서는 써보지 못한 기능들을 구현해보는 재미가 있었습니다.
- 이용자가 업무를 수행함에 있어 편의성을 위해 필터링을 더 많이 넣었어야하는 고민도 있었지만, 시간이 부족하여 넣지못한 점이 아쉬웠습니다.
- 프로젝트를 진행하면서 마무리단계에서 팀원들이 각자 맡은 파트를 합치는 과정에서 에러도 많이 났습니다. 그러면서 저도 미처 확인하지못한 상세사항들에 대해서 확인이 잘 되었습니다.
- 새로운 편집도구 intellij를 쓰면서 편리함을 너무 느끼고 gradle을 쓰면서 다양한 기능들을 활용할 수 있어 너무 재밌었습니다!
