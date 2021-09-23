package se.lexicon.workshop.g37_library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.lexicon.workshop.g37_library.model.AppUser;

import java.time.LocalDate;
import java.util.Collection;

public interface AppUserRepository extends CrudRepository <AppUser, Integer> {

    @Query("SELECT a FROM AppUser a WHERE UPPER(a.username) = UPPER(:username)")
    AppUser findAppUserByUsernameIgnoreCase(String username);

    @Query("SELECT a FROM AppUser a JOIN FETCH a.userDetails AS details WHERE UPPER(details.email) = UPPER(:email) ")
    AppUser findAppUserByUserDetails_EmailIgnoreCase(String email);


//    @Query("SELECT a FROM AppUser a WHERE a.userDetails.birthDate BETWEEN :start AND :end") //NOT WORKING YET
    Collection<AppUser> findAppUserByUserDetails_BirthDateBetween(LocalDate Start, LocalDate End);

    AppUser findAllAppUserByUserDetails_NameContainsIgnoreCase(String name);
}
