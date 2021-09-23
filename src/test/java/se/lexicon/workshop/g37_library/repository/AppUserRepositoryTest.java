package se.lexicon.workshop.g37_library.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.workshop.g37_library.model.AppUser;
import se.lexicon.workshop.g37_library.model.Details;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AppUserRepositoryTest {


    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AppUserRepository appUserRepository;


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
    void findAppUserByBirthdateBetween() {

        //Arrange
        LocalDate start = LocalDate.parse("1980-01-01");
        LocalDate end = LocalDate.parse("1995-01-01");

        //Act
        Collection<AppUser> foundAppUsers = appUserRepository.findAppUserByUserDetails_BirthDateBetween(start, end);

        //assert
        assertEquals(1, foundAppUsers.size());


    }
}