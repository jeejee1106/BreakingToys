# [부숴도 괜찮은 장난감]

### Main URL
- http://localhost:8080/swagger-ui/index.html -> root경로 호출 시 해당 url로 redirect되도록 설정 완료
- **참고
  - swagger-UI 버전에 따라 접속해야하는 주소가 다름
  - swagger-UI 2.x : http://localhost:8080/swagger-ui.html
  - swagger-UI 3.x : http://localhost:8080/swagger-ui/index.html

### H2 DataBase URL
http://localhost:8080/h2-console
db 연결 안됐다는 에러, 활성화 시키라는 에러가 뜨면 이 전에 다른 프로젝트에서 h2 db를 사용했던 적이 있나 살펴보고,
프로젝트 우클릭 후 reload from disk를 해준다.

### MEMO
1) delete와 deleteById의 차이점
  - delete
    - 이렇게 직접 delete 메서드와 findById메서드를 조합해서 쓸 수도 있다.
    - 장단점 : 로직이 좀 더 길어지지만 예외처리를 커스텀하여 개발자가 원하는 Exception과 메시지를 전달할 수 있다.
    - null입력 시  ExHandler로 커스텀한 400에러(BAD_REQUEST) 발생
  - deleteById
    - deleteById메서드 안에 delete메서드가 들어 있고, 그 안에서 또 findById메서드를 사용한다.
    - 장단점 : 로직이 메서드 하나로 끝나지만 내부적으로 처리되는 예외처리 방식을 따라야한다. (메시지 커스텀 못함)
    - 즉, 내부적으로 null체크를 해준다. (no값을 찾을 수 없을 경우 EmptyResultDataAccessException 을 발생시킴)
    - null입력 시 500에러 발생