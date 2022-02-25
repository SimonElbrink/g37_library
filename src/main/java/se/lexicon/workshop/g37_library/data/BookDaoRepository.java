package se.lexicon.workshop.g37_library.data;

import org.springframework.stereotype.Repository;
import se.lexicon.workshop.g37_library.model.Book;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class BookDaoRepository implements BookDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book findById(Integer integer) {
        return entityManager.find(Book.class, integer);
    }

    @Override
    public Collection<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public Collection<Book> findByBookTitle(String title) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE UPPER(b.title) LIKE UPPER(CONCAT('%', :title, '%'))", Book.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public void delete(Integer integer) {
        entityManager.remove(findById(integer));
    }
}
