package se.lexicon.workshop.g37_library.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.workshop.g37_library.model.Author;

import java.util.Collection;

public interface AuthorRepository extends CrudRepository<Author, Integer> {


    Collection<Author> findAllAuthorByWrittenBooksTitle(String bookTitle);
}
