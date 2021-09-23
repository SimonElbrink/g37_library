package se.lexicon.workshop.g37_library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.lexicon.workshop.g37_library.model.Author;

import java.util.Collection;

public interface AuthorRepository extends CrudRepository<Author, Integer> {


    @Query("SELECT a FROM Author a JOIN FETCH a.writtenBooks AS writtenBooks WHERE writtenBooks.title = :bookTitle")
    Collection<Author> findAllAuthorByWrittenBooksTitle(String bookTitle);

    @Query("SELECT a FROM Author a WHERE UPPER(a.firstName) LIKE UPPER(CONCAT('%',:name,'%')) " +
            "OR " +
            "UPPER(a.lastName) LIKE UPPER(CONCAT('%',:name,'%'))")
    Collection<Author> findAllAuthorsByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String name);
}