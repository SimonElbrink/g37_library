package se.lexicon.workshop.g37_library.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {


    List<Book> books = Arrays.asList(
            new Book("hsdfgd","Harry Potter 1", 15),
            new Book("fgfsj2","Harry Potter 2", 15),
            new Book("sadfs","Harry Potter 3", 15),
            new Book("fhdsf","Harry Potter 4", 15),
            new Book("dfhhdr","Harry Potter 5", 15),
            new Book("yfght","Harry Potter 6", 15),
            new Book("sdfgrr","Harry Potter 7", 15),
            new Book("asdss","The Hobbits", 16)
    );

    List<BookLoan> bookLoans = Arrays.asList(
            new BookLoan(books.get(0)),
            new BookLoan(books.get(1)),
            new BookLoan(books.get(2)),
            new BookLoan(books.get(3)),
            new BookLoan(books.get(4)),
            new BookLoan(books.get(5)),
            new BookLoan(books.get(6))

    );

    List<AppUser> appUsers = Arrays.asList(
            new AppUser("ulfbengtsson","ThisIsAnOtherPassword", null)
    );

    private AppUser testObject;

    @BeforeEach
    void setUp() {

        testObject = new AppUser(0, "simonelbrink", "DontLookAtMyPassword",
                null, null, new ArrayList<>(bookLoans.subList(0, 7)));
    }

    @Test
    void addBookLoan() {
        // AppUser Book, BookLoan
        int sizeOfAppUserLoanList = 8;
        BookLoan bookLoan = new BookLoan(books.get(7));

        testObject.addBookLoan(bookLoan);

        assertAll(
                ()-> assertEquals(sizeOfAppUserLoanList,testObject.getLoans().size()),
                () -> assertTrue(testObject.getLoans().contains(bookLoan), "testObject did not contain bookloan"),
                () -> assertEquals(testObject.getLoans().get(testObject.getLoans().size() -1).getBook(), bookLoan.getBook(), "testObject did not contain BookLoan")
        );
    }

    @Test
    void removeBookLoan() {


    }

    @Test
    void setLoans() {
    }

    @Test
    void getLoans() {
    }
}