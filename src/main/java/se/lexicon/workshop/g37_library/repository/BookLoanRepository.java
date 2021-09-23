package se.lexicon.workshop.g37_library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.lexicon.workshop.g37_library.model.BookLoan;

import java.util.Collection;

public interface BookLoanRepository extends CrudRepository<BookLoan, Integer> {

    @Query("SELECT b FROM BookLoan b WHERE b.returned = false")
    Collection<BookLoan> findAllBookLoansByReturnedFalse();

    @Query("SELECT b FROM BookLoan b WHERE b.returned = true")
    Collection<BookLoan> findAllBookLoansByReturnedTrue();

    @Query("SELECT b FROM BookLoan b WHERE b.returned = :returned")
    Collection<BookLoan> findAllBookLoansByReturned(boolean returned);


}
