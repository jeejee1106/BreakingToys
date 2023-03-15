package com.toy.library.repository;

import com.toy.library.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
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

    public Optional<Book> findById(Long no) {
        Book book = em.find(Book.class, no);
        return Optional.ofNullable(book);
    }

    public void deleteById(Book book) {
        em.remove(book);
    }

    public List<Long> count() {
        return em.createQuery("select count(b) from Book b", Long.class)
                .getResultList();
    }

}
