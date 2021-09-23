package se.lexicon.workshop.g37_library.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.workshop.g37_library.model.Book;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    private TestEntityManager entityManager;
    private BookRepository bookDAO;

    @Autowired
    public BookRepositoryTest(TestEntityManager entityManager, BookRepository bookDAO) {
        this.entityManager = entityManager;
        this.bookDAO = bookDAO;
    }

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
            new Book("dffads","Lord Of The Rings 3", 14)
    );

    @BeforeEach
    void setUp() {

        books = books.stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());

    }

    @Test
    void create() {
        // Arrange
        Book book = new Book(0,"isbnNumber1012", "NewBook", 10, true, new HashSet<>());
        // Act
        Book persistedBook = bookDAO.save(book);
        // Assert
        assertAll(
                () -> assertTrue(persistedBook.getBookId() != 0),
                () -> assertEquals(book.getIsbn(), persistedBook.getIsbn()),
                () -> assertEquals(book.getTitle(), persistedBook.getTitle()),
                () -> assertEquals(book.getMaxLoanDays(), persistedBook.getMaxLoanDays()),
                () -> assertEquals(book.isAvailable(), persistedBook.isAvailable()),
                () -> assertEquals(book.getAuthors(), persistedBook.getAuthors())
        );

    }

    @Test
    void findByBookTitle() {
        // Arrange
        String bookToFind = "Harry Potter";
        Integer numberOfBooks = 7;
        // Act
        Collection<Book> byBookTitle = bookDAO.findAllBookByTitleContains(bookToFind);
        // Assert
        assertNotNull(byBookTitle);
        assertEquals(numberOfBooks, byBookTitle.size());

    }
}