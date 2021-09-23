package se.lexicon.workshop.g37_library.data.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop.g37_library.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AuthorDAORepository implements AuthorDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public Collection<Author> findAllAuthorByBookName(String bookTitle) {
        return null;
    }

    @Override
    @Transactional
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author findById(Integer integer) {
        return entityManager.find(Author.class, integer);
    }

    @Override
    @Transactional
    public Collection<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return entityManager.merge(author);
    }

    @Override
    @Transactional
    public void delete(Integer integer) {
        entityManager.remove(findById(integer));
    }
}
