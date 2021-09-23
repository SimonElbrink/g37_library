package se.lexicon.workshop.g37_library.data.dao;

import se.lexicon.workshop.g37_library.model.Book;

import java.util.Collection;

public interface BookDAO extends GenericDAO<Book, Integer>{
    @Override
    Book create(Book book);

    @Override
    Book findById(Integer integer);

    @Override
    Collection<Book> findAll();


    Collection<Book> findByBookTitle(String title);

    @Override
    Book update(Book book);

    @Override
    void delete(Integer integer);
}
