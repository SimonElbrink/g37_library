package se.lexicon.workshop.g37_library.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop.g37_library.data.dao.AuthorDAO;
import se.lexicon.workshop.g37_library.model.Author;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
class AuthorDAORepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorDAO testObject;


    List<Author> authorList = Arrays.asList(
            new Author("J.R.R.", "Tolkien"),
            new Author("J.K.", "Rowling"),
            new Author("Steven", "Kong")
    );


    @BeforeEach
    void setUp() {
        authorList = authorList.stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());
    }

    @Test
    void findAllAuthorByBookName() {
    }

    @Test
    void create() {
        // Arrange
        Author toPersist = new Author("Test", "Testsson");

        // Act
        toPersist = testObject.create(toPersist);

        // Assert
        assertTrue(toPersist.getAuthorId() != 0);
    }

    @Test
    void findById() {
        // Arrange
        Author toFind = authorList.get(1);

        // Act
        Author foundAuthor = testObject.findById(toFind.getAuthorId());

        // Assert
        assertEquals(toFind, foundAuthor);
        assertEquals("J.K.", foundAuthor.getFirstName());
    }

    @Test
    void findAll() {
        //Arrange
        int elementsCount = authorList.size();

        //Act
        Collection<Author> foundAuthors = testObject.findAll();

        //Assert
        assertEquals(elementsCount, foundAuthors.size());
    }

    @Test
    void update() {
        //Arrange
        Author authorOriginal = entityManager.find(Author.class, authorList.get(2).getAuthorId());

        Author toUpdate = new Author(authorOriginal.getAuthorId(),"King", "Kong", new HashSet<>());

        //Act
        Author updated = testObject.update(toUpdate);

        //Arrange
        assertEquals(toUpdate.getAuthorId(), updated.getAuthorId());
        assertEquals("King",updated.getFirstName());
        assertEquals("Kong",updated.getLastName());
    }

    @Test
    void delete() {
        Author toRemove = entityManager.find(Author.class, authorList.get(2).getAuthorId());

        testObject.delete(toRemove.getAuthorId());

        Author findingRemovedAuthor = entityManager.find(Author.class, toRemove.getAuthorId());

        assertNull(findingRemovedAuthor);
    }
}