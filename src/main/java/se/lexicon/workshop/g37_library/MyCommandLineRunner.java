package se.lexicon.workshop.g37_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop.g37_library.model.*;
import se.lexicon.workshop.g37_library.model.form.AppUserForm;
import se.lexicon.workshop.g37_library.repository.AuthorRepository;
import se.lexicon.workshop.g37_library.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.*;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    private final AuthorRepository repository;
    private final BookRepository bookRepository;

@Autowired
    public MyCommandLineRunner(AuthorRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {



        Book lord_of_the_rings = new Book("0618260587", "Lord Of the Rings", 15);
        entityManager.persist(lord_of_the_rings);

        Author j_r_r = new Author("J.R.R", "Tolkien");
        entityManager.persist(j_r_r);

        //convenience method
        j_r_r.addBook(lord_of_the_rings);

        BookLoan bookLoan = new BookLoan(lord_of_the_rings);
        entityManager.persist(bookLoan);

//        AppUser simon_elbrink = new AppUser(0,"simonelbrink", "Don'tLookAtMyPassword", LocalDate.now(),
//                new Details("simon@lexicon.se", "Simon Elbrink", LocalDate.parse("1997-03-18")),
//                null
//        );
        //Way 1 - Manually setting bidirectional relationships
//        bookLoan.setBorrower(simon_elbrink);
//        simon_elbrink.getLoans().add(bookLoan);


        //Way 2 - Convenience method
//        simon_elbrink.addBookLoan(bookLoan);


        // Way 3 Adding relationship management in Constructor.
        AppUser simon_elbrink = new AppUser(0,"simonelbrink", "Don'tLookAtMyPassword", LocalDate.now(),
                new Details("simon@lexicon.se", "Simon Elbrink", LocalDate.parse("1997-03-18")),
                new ArrayList<>(Collections.singletonList(bookLoan))
        );

        entityManager.persist(simon_elbrink);

        //----

        AppUserForm appUserForm = new AppUserForm("charles", "hello World", LocalDate.now(),
                "Test@Test.com", "Charles Rudahusha", LocalDate.parse("1997-03-18"));

        AppUser convertingToAppUser = new AppUser(0,appUserForm.getUsername(), appUserForm.getPassword(), appUserForm.getRegDate(),
         new Details(appUserForm.getEmail(), appUserForm.getFullName(), appUserForm.getBirthDate()), null);

        entityManager.persist(convertingToAppUser);




            Book harry_potter1 = new Book("9789129697704", "Harry Potter", 15);

            Optional<Book> bookByTitle = bookRepository.findBookByIsbn(harry_potter1.getIsbn());

            if (bookByTitle.isPresent()){
                harry_potter1 = bookByTitle.get();

            }else{
                entityManager.persist(harry_potter1);
//                BookLoan lendHarryPotter = new BookLoan(harry_potter1);
//                simon_elbrink.addBookLoan(lendHarryPotter);
//                entityManager.persist(lendHarryPotter);
            }




//        System.out.println(bookByTitle);

//
//        Collection<Author> harry_potter = repository.findAllAuthorByWrittenBooksTitle("Harry Potter");
//
//        harry_potter.forEach(System.out::println);


//        System.out.println("simon_elbrink = " + simon_elbrink.getLoans().get(0));
//        System.out.println("bookLoan = " + bookLoan.toString());


    }
}
