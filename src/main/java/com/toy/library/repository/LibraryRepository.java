package com.toy.library.repository;

import com.toy.library.entity.Library;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LibraryRepository {

    @PersistenceContext
    private EntityManager em;

    public Library save(Library library) {
        em.persist(library);
        return library;
    }

}
