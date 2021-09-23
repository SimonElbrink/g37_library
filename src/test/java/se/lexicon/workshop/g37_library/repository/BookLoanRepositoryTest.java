package se.lexicon.workshop.g37_library.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.workshop.g37_library.model.Book;
import se.lexicon.workshop.g37_library.model.BookLoan;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookLoanRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookLoanRepository testObject;


    List<Book> books = Arrays.asList(
            new Book("hsdfgd","Harry Potter 1", 15),
            new Book("fgfsj2","Harry Potter 2", 15)
    );

    List<BookLoan> bookLoanList = Arrays.asList(
            new BookLoan(books.get(0)),
            new BookLoan(books.get(1)),
            new BookLoan(books.get(1))
            );

    @BeforeEach
    void setUp() {

        books = books.stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());

        bookLoanList = bookLoanList.stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());

    }

    @Test
    void findAllBookLoanByReturnedFalse() {
        // Arrange
        // Act
        Collection<BookLoan> foundBookLoans = testObject.findAllBookLoansByReturnedFalse();

        // Assert
        assertEquals(3, foundBookLoans.size());
    }

    @Test
    void findAllBookLoanByReturnedTrue() {
        //Arrange
        bookLoanList.forEach( bookLoan -> bookLoan.setReturned(true));

        // Act
        Collection<BookLoan> foundBookLoans = testObject.findAllBookLoansByReturnedTrue();

        // Assert
        assertEquals(3, foundBookLoans.size());

    }
}