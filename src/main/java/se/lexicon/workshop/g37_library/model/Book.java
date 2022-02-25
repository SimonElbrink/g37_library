package se.lexicon.workshop.g37_library.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity // Making a table
public class Book {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Strategy for generating Id.
    private int bookId;
    private String isbn;
    private String title;
    private int maxLoanDays;
    private boolean available;

    // Specifying to hibernate how the relationship should be setup in the Database
    @ManyToMany (cascade = {DETACH, MERGE, REFRESH, PERSIST},
            fetch = FetchType.LAZY,
    mappedBy = "writtenBooks")
    private Set<Author> authors;

    //Empty constructor is mandatory.
    protected Book() {
    }

    public Book(String isbn, String title, int maxLoanDays) {
        this(0, isbn, title, maxLoanDays,true, new HashSet<>());
    }

    public Book(int bookId, String isbn, String title, int maxLoanDays, boolean available, Set<Author> authors) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
        this.available = available;
        this.authors = authors;
    }

    //Convenience method for adding Author to a book in a bidirectional way.
    public void addAuthor(Author author){
        if(author == null) throw new IllegalArgumentException("Author can not be null!");
        if (authors == null) authors = new HashSet<>();

        if (!authors.contains(author)){
            authors.add(author);
            author.getWrittenBooks().add(this);
        }
    }

    //Convenience method for removing Author from a book in a bidirectional way.
    public void removeAuthor(Author author){
        if(author == null) throw new IllegalArgumentException("Author can not be null!");
        if (authors == null) authors = new HashSet<>();

        if (authors.contains(author)){
            authors.remove(author);
            author.getWrittenBooks().remove(this);
        }
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<Author> getAuthors() {
        if (authors == null) authors = new HashSet<>();
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        if (authors == null) authors = new HashSet<>();

        if (authors.isEmpty()){
            if (this.authors != null){
                for (Author author : this.authors){
                    author.writtenBooks.remove(this);
                }
            }
        }else{
            for (Author author : this.authors){
                author.writtenBooks.add(this);
            }
        }
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getMaxLoanDays() == book.getMaxLoanDays() && isAvailable() == book.isAvailable() && Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getTitle(), book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getTitle(), getMaxLoanDays(), isAvailable());
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                ", available=" + available +
                '}';
    }
}
