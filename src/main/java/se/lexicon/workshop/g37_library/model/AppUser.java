package se.lexicon.workshop.g37_library.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;
    private String username;
    private String password;
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Details userDetails;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "borrower")
    private List<BookLoan> loans;

    protected AppUser() {
    }

    public AppUser(int appUserId, String username, String password, LocalDate regDate, Details userDetails, List<BookLoan> loans) {
        setAppUserId(appUserId);
        setUsername(username);
        setPassword(password);
        setRegDate(regDate);
        setUserDetails(userDetails);

        // way 3
//        for (BookLoan b: loans) {
//            addBookLoan(b);
//        }

        //Way 4 - Modify the Setter.
        setLoans(loans);
    }

    public AppUser(String username, String password, Details userDetails) {
        this(0, username, password, LocalDate.now(), userDetails, new ArrayList<>());

    }

    /**
     * Makes sure that borrower and BookLoan is added in both classes.
     * Maintaining the relationship.
     * Also changing book to not available.
     * @param bookLoan
     * @return
     */
    public boolean addBookLoan(BookLoan bookLoan) {
        if (bookLoan == null) throw new IllegalArgumentException("Param bookLoan was null");
        if (loans == null) loans = new ArrayList<>();

        if (bookLoan.getBook().isAvailable()){
            if (!loans.contains(bookLoan)) {
                bookLoan.setBorrower(this);
                bookLoan.getBook().setAvailable(false);
                this.loans.add(bookLoan);
                return true;
            }
        }

        return false;
    }


    public boolean removeBookLoan(BookLoan bookLoan) {
        if (bookLoan == null) throw new IllegalArgumentException("Param bookLoan was null");
        if (loans == null) loans = new ArrayList<>();

        if (this.loans.remove(bookLoan)) {
            bookLoan.setBorrower(null);
            bookLoan.getBook().setAvailable(true);
            bookLoan.setReturned(true);
            return true;
        }

        return false;

    }

//    public void setLoans(List<BookLoan> loans) {
//        this.loans = loans;
//    }

    public void setLoans(List<BookLoan> loans) {
        if (loans == null) loans = new ArrayList<>();
        if (this.loans == null) this.loans = new ArrayList<>();


        while (loans.contains(null)) {
            loans.remove(null);
        }


        if (loans.isEmpty()) {
            if (this.loans != null) {
                for (BookLoan loan : this.loans) {
                    loan.setBorrower(null);
                }
            }
        } else {
            for (BookLoan loan : loans) {
                loan.getBook().setAvailable(false);
                loan.setBorrower(this);
            }
        }

        this.loans = loans;
    }

    public List<BookLoan> getLoans() {
        if (loans == null) loans = new ArrayList<>();
        return loans;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(getUsername(), appUser.getUsername()) && Objects.equals(getPassword(), appUser.getPassword()) && Objects.equals(getRegDate(), appUser.getRegDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getRegDate());
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", userDetails=" + userDetails +
                '}';
    }
}
