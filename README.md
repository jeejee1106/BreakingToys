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
<details>
<summary><b> 1) delete와 deleteById의 차이점 (Spring Data JPA) </b></summary>

- delete
  - 이렇게 직접 delete 메서드와 findById메서드를 조합해서 쓸 수도 있다.
  - 장단점 : 로직이 좀 더 길어지지만 예외처리를 커스텀하여 개발자가 원하는 Exception과 메시지를 전달할 수 있다.
  - null입력 시  ExHandler로 커스텀한 400에러(BAD_REQUEST) 발생
- deleteById
  - deleteById메서드 안에 delete메서드가 들어 있고, 그 안에서 또 findById메서드를 사용한다.
  - 장단점 : 로직이 메서드 하나로 끝나지만 내부적으로 처리되는 예외처리 방식을 따라야한다. (메시지 커스텀 못함)
  - 즉, 내부적으로 null체크를 해준다. (no값을 찾을 수 없을 경우 EmptyResultDataAccessException 을 발생시킴)
  - null입력 시 500에러 발생
</details>

<details>
<summary><b> 2) jpql을 사용한 update (벌크연산) </b></summary>

  - Book의 논리적 삭제를 진행하면서 파라미터로 받은 BookNo에 해당하는 delYn컬럼을 Y로 바꿔주어야하는 상황이 생김
  - setter나 다른 메서드를 사용하지 않고 jpql로 update문을 짜줌. (why? 아직 jpa를 많이 공부하지 못하고 간단한 jpql까지 배웠기 때문에 jpql로 직접 로직을 짜서 해결해보고 싶었다.)
       ```java
      /* 첫 번째 로직 */
      public void deleteByIdLogical(Long bookNo) {
           em.createQuery("update Book b set b.delYn = 'Y' where b.bookNo = :bookNo")
                   .setParameter("bookNo", bookNo);
       }
       ```
  - 그러나 select쿼리도, update쿼리도 나가지 않는 문제 발생
  - 1차 캐시에 없어서 그런가...? 싶은 생각에 find로직을 추가했다.
      ```java
      /* 두 번째 로직 */
      public void deleteByIdLogical(Long bookNo) {
           Book book = em.find(bookNo);
           em.createQuery("update Book b set b.delYn = 'Y' where b.bookNo = :bookNo")
                   .setParameter("bookNo", book.getBookNo());
           em.persist(book);
       }
      ```
  - persist()도 변경감지로 인해 안해줘도 된댔는데 긴가민가.. 그래도 해보자.. 라는 생각으로 추가해줬다.
  - em.find로 인해 select쿼리는 나오지만 여전히 update쿼리는 안나왔다.
  - 구글링을 통해 executeUpdate()를 추가.
      ```java
      /* 세 번째 로직 */
      public void deleteByIdLogical(Long bookNo) {
           em.createQuery("update Book b set b.delYn = 'Y' where b.bookNo = :bookNo")
                   .setParameter("bookNo", bookNo)
                   .executeUpdate();
       }
      ```
  - 이 땐 update쿼리가 잘 나가고 db에도 잘 반영이 되는 것을 확인했다.
  - 그런데 내가 공부하기로는 update를 할 땐 @Id값을 넘겨주면 jpa가 해당 값을 확인한 후(select) db에 값이 없으면 insert를, 값이 있으면 변경된 부분만 update 쿼리를 날린다고 알고 있었다.  그렇지만 위 방법에선 update쿼리만 딱 실행되었다.
  - 그 이유는 jpql은 sql로 변환되어 쿼리를 DB에 바로 보내기 때문이다!!
  - 즉, 해당 방법은 EntityManager를 사용하지 않았기 때문에 영속성 컨텍스트에서 엔티티를 찾지 않는다. 변경감지를 통한 update가 아니라 벌크연산을 사용하는 방법이라고 한다.
  - 벌크연산은 대량의 데이터(여러건의 데이터)를 한 번에 수정하거나 삭제할 때 사용하는 방법이라고 한다.
  - 대량의 데이터를 수정할 때 변경감지를 사용한다면 데이터 갯수 만큼의 update쿼리가 나가게 되는데, 이를 보완하기 위해 나왔다.
  - `벌크 연산은 영속성 컨텍스트를 무시하고 DB에 직접 쿼리 한다는 점을 주의해야 한다. 즉, DB에 반영된 변경이 영속성 컨텍스트에는 반영되지 않는다는 말이다.`
</details>

<details>
<summary><b> 3) JPA에서 setter없이 update하기 </b></summary>

- 참고: setter없이 update를 하는 이유?
  - setter를 사용하면 값의 변화점을 예측하기 힘들어진다.
  - 지금 당장에는 문제가 생기지 않아도 추후 setter로 인한 문제가 발생할 여지를 남기지 않는 것이 좋다!
- 그렇다면 어떻게 setter없이 update를 할 수 있을까?
- `update가 필요한 값만을 받는 메서드를 따로 생성해서 그것을 명시적으로 사용하는 방법이 있다.`
  - 먼저 Entity에 변화가 필요한 값만을 받는 메서드를 생성해해준다. 이 때, 파라미터로는 id 값과(@Id) update할 dto등을 받아준다.
  - Service단에서는 findById()로 객체를 가져오면, 이 객체는 영속성 컨테이너에 들어가게 된다.
  - 영속성 컨테이너에 들어간다는 것? EntityManager가 해당 객체를 감시(?)!
  - EntityManager는 findById()를 통해 가져온 값을 스냅샷으로 보관. 다음 커밋이 이루어질 때 보관된 스냅샷과 커밋될 때의 값을 비교하고,
  - 이 때 값이 변경되었으면 값이 변경되었음을 감지하고 데이터에 대한 UPDATE를 해준다는 것을 의미한다.(더티 체킹!)
</details>