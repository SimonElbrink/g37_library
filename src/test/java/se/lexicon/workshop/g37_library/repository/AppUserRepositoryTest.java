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
import java.util.List;
import java.util.stream.Collectors;

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


}