package com.toy.library.repository;

import com.toy.library.entity.Book;
import com.toy.library.entity.Library;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class LibraryRepository {

    @PersistenceContext
    private EntityManager em;

    public Library save(Library library) {
        em.persist(library);
        return library;
    }

    public List<Library> findAll() {
        return em.createQuery("select l from Library l", Library.class)
                .getResultList();
    }

    public Optional<Library> findById(Long libraryNo) {
        Library library = em.find(Library.class, libraryNo);
        return Optional.ofNullable(library);
    }

}
