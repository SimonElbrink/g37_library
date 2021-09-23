package se.lexicon.workshop.g37_library.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.workshop.g37_library.model.BookLoan;

import java.util.Collection;

public interface BookLoanRepository extends CrudRepository<BookLoan, Integer> {

    Collection<BookLoan> findAllBookLoansByReturnedFalse();
    Collection<BookLoan> findAllBookLoansByReturnedTrue();

    Collection<BookLoan> findAllBookLoansByReturned(boolean returned);


}
