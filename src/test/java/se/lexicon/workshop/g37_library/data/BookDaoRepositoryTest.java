package se.lexicon.workshop.g37_library.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop.g37_library.model.AppUser;
import se.lexicon.workshop.g37_library.model.Book;
import se.lexicon.workshop.g37_library.model.Details;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class BookDaoRepositoryTest {


    private TestEntityManager entityManager;
    private BookDAO bookDAO;

    @Autowired
    public BookDaoRepositoryTest(TestEntityManager entityManager, BookDAO bookDAO) {
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
        Book book = new Book(0,"isbnNumber1012", "NewBook", 10, new HashSet<>());
        // Act
        Book persistedBook = entityManager.persist(book);
        // Assert
        assertAll(
                () -> assertTrue(persistedBook.getBookId() != 0),
                () -> assertEquals(book.getIsbn(), persistedBook.getIsbn()),
                () -> assertEquals(book.getTitle(), persistedBook.getTitle()),
                () -> assertEquals(book.getMaxLoanDays(), persistedBook.getMaxLoanDays())
        );

    }

    @Test
    void findById() {
        // Arrange
        Book toFind = books.get(0);

        // Act
        Book foundBook = bookDAO.findById(toFind.getBookId());

        // Assert
        assertEquals(toFind,foundBook, "The books was not Equal");
    }

    @Test
    void findAll() {
        // Arrange
        int countElements = books.size();
        // Act
        Collection<Book> foundBooks = bookDAO.findAll();

        // Assert
        assertEquals(countElements, foundBooks.size());
    }

    @Test
    void findByBookTitle() {
        // Arrange
        String bookToFind = "Harry Potter";
        Integer numberOfBooks = 7;
        // Act
        Collection<Book> byBookTitle = bookDAO.findByBookTitle(bookToFind);
        // Assert
        assertNotNull(byBookTitle);
        assertEquals(numberOfBooks, byBookTitle.size());

    }

    @Test
    void update() {
        //Arrange
        Book bookOriginal = entityManager.find(Book.class, books.get(8).getBookId());
        Book bookUpdated = new Book(bookOriginal.getBookId(),"9780261102354","Lord Of The Rings - The Fellowship of the Ring", 14, new HashSet<>());

        //Act
        Book thatIsUpdated = bookDAO.update(bookUpdated);

        //Assert
        assertAll(
                () -> assertNotNull(thatIsUpdated),
                () -> assertEquals(bookUpdated, thatIsUpdated)
        );

    }

    @Test
    void delete() {
        Book toDelete = entityManager.find(Book.class,books.get(0).getBookId());

        bookDAO.delete(toDelete.getBookId());

        Book deletedBook = entityManager.find(Book.class,books.get(0).getBookId());

        assertNull(deletedBook);
    }
}