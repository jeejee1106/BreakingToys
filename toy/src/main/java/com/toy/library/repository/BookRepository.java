package com.toy.library.repository;

import com.toy.library.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
//@RequiredArgsConstructor //EntityManager @PersistenceContext 대신 생성자 주입처럼 사용가능
public class BookRepository {

    @PersistenceContext
    private EntityManager em;

    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    public Optional<Book> findById(Long bookNo) {
        Book book = em.find(Book.class, bookNo);
        return Optional.ofNullable(book);
    }

    public void deleteByIdPhysical(Book book) {
        //쿼리문이 나가는 시점을 보기위한 로그
        log.info(">>>>>>>>>>>>>>>>>>");
//        Book book = em.find(Book.class, bookNo); //서비스단에서 findById로 이미 한 번 select를 진행하여 엔티티를 영속컨테이너에 올리기 때문에 얘는 필요가 없다.
        em.remove(book);
        log.info(">>>>>>>>>>>>>>>>>>");
    }

    public void deleteByIdLogical(Long bookNo) {
//        Book.deleteByIdLogical(bookNo);
//        em.persist(book);

//        em.createQuery("update Book b set b.delYn = 'Y' where b.bookNo = :bookNo")
//                .setParameter("bookNo", bookNo)
//                .executeUpdate(); //이렇게 사용하는 것은 벌크연산. 쿼리 한번으로 여러 테이블의 로우를 변경 가능.
    }

    public Long count() {
        return em.createQuery("select count(b) from Book b", Long.class)
                .getSingleResult();
    }

}
