package se.lexicon.workshop.g37_library.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;

    @ManyToOne (cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER )
    private AppUser borrower;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private Book book;

    protected BookLoan() {
    }

    public BookLoan(Book book) {
        this(0, LocalDate.now(),null,false,null, book);
        calculateDueDate();
    }

    public BookLoan(int loanId, LocalDate loanDate, LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        this.loanId = loanId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }

    void calculateDueDate(){
        setDueDate(
                loanDate.plus(Period.ofDays(book.getMaxLoanDays()))
        );

    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public AppUser getBorrower() {
        return borrower;
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return isReturned() == bookLoan.isReturned() && Objects.equals(getLoanDate(), bookLoan.getLoanDate()) && Objects.equals(getDueDate(), bookLoan.getDueDate()) && Objects.equals(getBorrower(), bookLoan.getBorrower()) && Objects.equals(getBook(), bookLoan.getBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoanDate(), getDueDate(), isReturned(), getBorrower(), getBook());
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "loanId=" + loanId +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                ", borrower=" + borrower +
                ", book=" + book +
                '}';
    }
}
