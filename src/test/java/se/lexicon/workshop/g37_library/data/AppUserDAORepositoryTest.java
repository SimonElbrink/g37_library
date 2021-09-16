package se.lexicon.workshop.g37_library.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop.g37_library.model.AppUser;
import se.lexicon.workshop.g37_library.model.Details;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class AppUserDAORepositoryTest {


    TestEntityManager entityManager;

    List<AppUser> appUsers = Arrays.asList(
            new AppUser("simonElbrink", "DontLookAtMyPassword",
                    new Details("simon@lexicon.se", "Simon Elbrink", LocalDate.parse("1997-03-18"))
            ),
            new AppUser("ulfbengtsson","ThisIsAnOtherPassword",
                    new Details("ulf@lexicon.se","Ulf Bengtsson", LocalDate.parse("1982-08-25")))

    );

    @BeforeEach
    void setUp() {

        appUsers = appUsers.stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}