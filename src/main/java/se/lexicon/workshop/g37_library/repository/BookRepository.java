package se.lexicon.workshop.g37_library.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.workshop.g37_library.model.Book;

import java.util.Collection;

public interface BookRepository extends CrudRepository<Book, Integer> {


    Collection<Book> findAllBookByTitleContains(String title);
    Collection<Book> findAllBooksByAvailable(boolean available);



}
