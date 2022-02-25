package se.lexicon.workshop.g37_library.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    private String firstName;
    private String lastName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    Set<Book> writtenBooks;


    protected Author() {
    }

    public Author(String firstName, String lastName) {
        this(0, firstName, lastName, new HashSet<>());
    }

    public Author(int authorId, String firstName, String lastName, Set<Book> writtenBooks) {
        setAuthorId(authorId);
        setFirstName(firstName);
        setLastName(lastName);
        setWrittenBooks(writtenBooks);
    }

    public void addBook(Book book){

        if(book == null) throw new IllegalArgumentException("Book can not be null!");
        if (writtenBooks == null) writtenBooks = new HashSet<>();

        if (!writtenBooks.contains(book)){
            writtenBooks.add(book);
            book.getAuthors().add(this);
        }
    }
    public void removeBook(Book book){

        if(book == null) throw new IllegalArgumentException("Book can not be null!");
        if (writtenBooks == null) writtenBooks = new HashSet<>();

        if (writtenBooks.contains(book)){
            book.getAuthors().remove(this);
            writtenBooks.remove(book);
        }
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getWrittenBooks() {
        if (writtenBooks == null) writtenBooks = new HashSet<>();
        return writtenBooks;
    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(getFirstName(), author.getFirstName()) && Objects.equals(getLastName(), author.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
