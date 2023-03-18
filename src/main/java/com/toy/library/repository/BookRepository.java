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

    public void deleteByIdPhysical(Long bookNo) {
        Book book = em.find(Book.class, bookNo);
        em.remove(book);
    }

    public void deleteByIdLogical(Long bookNo) {
        em.createQuery("update Book b set b.delYn = 'Y' where b.bookNo = :bookNo")
                .setParameter("bookNo", bookNo)
                .executeUpdate();
    }

    public Long count() {
        return em.createQuery("select count(b) from Book b", Long.class)
                .getSingleResult();
    }

}
