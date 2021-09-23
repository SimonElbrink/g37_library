package se.lexicon.workshop.g37_library.data.dao;

import se.lexicon.workshop.g37_library.model.Author;

import java.util.Collection;

public interface AuthorDAO extends GenericDAO<Author, Integer>{


    Collection<Author> findAllAuthorByBookName(String bookTitle);
}
