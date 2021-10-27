package se.lexicon.workshop.g37_library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.lexicon.workshop.g37_library.model.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findBookByTitle(String bookTitle);

    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%', :title ,'%') ")
    Collection<Book> findAllBooksByTitleContains(String title);

    @Query("SELECT b FROM Book b WHERE b.available = :available")
    Collection<Book> findAllBooksByAvailable(boolean available);


    Optional<Book> findBookByIsbn(String isbn);
}

