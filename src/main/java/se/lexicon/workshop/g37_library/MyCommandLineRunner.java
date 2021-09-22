package se.lexicon.workshop.g37_library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop.g37_library.model.AppUser;
import se.lexicon.workshop.g37_library.model.Book;
import se.lexicon.workshop.g37_library.model.BookLoan;
import se.lexicon.workshop.g37_library.model.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Book lord_of_the_rings = new Book("374828390", "Lord Of the Rings", 15);
        entityManager.persist(lord_of_the_rings);

        BookLoan bookLoan = new BookLoan(lord_of_the_rings);
        entityManager.persist(bookLoan);

        // Way 3 Adding relationship management in Contructor.
        AppUser simon_elbrink = new AppUser(0,"simonelbrink", "Don'tLookAtMyPassword", LocalDate.now(),
                new Details("simon@lexicon.se", "Simon Elbrink", LocalDate.parse("1997-03-18")),
                Arrays.asList(bookLoan)
        );


        //Way 1
//        bookLoan.setBorrower(simon_elbrink);
//        simon_elbrink.getLoans().add(bookLoan);


        //Way 2
//        simon_elbrink.addBookLoan(bookLoan);

        entityManager.persist(simon_elbrink);



        System.out.println("simon_elbrink = " + simon_elbrink.getLoans().get(0));
        System.out.println("bookLoan = " + bookLoan.toString());


    }
}
