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
