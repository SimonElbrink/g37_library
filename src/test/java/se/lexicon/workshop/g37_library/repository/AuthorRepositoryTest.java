package se.lexicon.workshop.g37_library.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.workshop.g37_library.model.Book;
import se.lexicon.workshop.g37_library.repository.AuthorRepository;
import se.lexicon.workshop.g37_library.model.Author;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository testObject;


    List<Author> authorList = Arrays.asList(
            new Author("J.R.R.", "Tolkien"),
            new Author("J.K.", "Rowling"),
            new Author("Steven", "Kong"),
            new Author("Jeanne", "Boyarsky"),
            new Author("Scott", "Selikoff")
    );

    List<Book> books = Arrays.asList(
            new Book("hsdfgd","Harry Potter 1", 15),
            new Book("fgfsj2","Harry Potter 2", 15),
            new Book("sadfs","Harry Potter 3", 15),
            new Book("fhdsf","Harry Potter 4", 15),
            new Book("dfhhdr","Harry Potter 5", 15),
            new Book("yfght","Harry Potter 6", 15),
            new Book("sdfgrr","Harry Potter 7", 15),
            new Book("asdss","The Hobbits", 16),
            new Book("dfsdh","Lord Of The Rings 1", 14),
            new Book("dffrr","Lord Of The Rings 2", 14),
            new Book("dffads","Lord Of The Rings 3", 14),
            new Book("978-1-118-95740-0", "OCA Study Guide",90)
    );


    @BeforeEach
    void setUp() {

        authorList.get(1).addBook(books.get(0));
        authorList.get(1).addBook(books.get(1));
        authorList.get(1).addBook(books.get(2));
        authorList.get(1).addBook(books.get(3));
        authorList.get(1).addBook(books.get(4));
        authorList.get(1).addBook(books.get(5));
        authorList.get(1).addBook(books.get(6));

        authorList.get(3).addBook(books.get(11));
        authorList.get(4).addBook(books.get(11));

        authorList = authorList.stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());


        books = books.stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());
    }

    @Test
    void findAllAuthorByWrittenBooksTitle_OneAuthor() {
        // Arrange
        String bookTitle = "Harry Potter";
        Collection<Author> foundAuthors = new ArrayList<>();

        // Act
        foundAuthors = testObject.findAllAuthorByWrittenBooksTitle(bookTitle);

        // Assert
        assertTrue(foundAuthors.contains(authorList.get(1)));
        assertEquals(1, foundAuthors.size());
        assertFalse(foundAuthors.isEmpty());
    }

    @Test
    void findAllAuthorsByBookTitle_multiAuthors(){
        String bookTitle = "OCA Study Guide";
        Collection<Author> foundAuthors = new ArrayList<>();


        foundAuthors = testObject.findAllAuthorByWrittenBooksTitle(bookTitle);

        assertEquals(2, foundAuthors.size());


    }

    @Test
    void create() {
        // Arrange
        Author toPersist = new Author("Test", "Testsson");

        // Act
        toPersist = testObject.save(toPersist);

        // Assert
        assertTrue(toPersist.getAuthorId() != 0);
    }
}