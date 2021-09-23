package se.lexicon.workshop.g37_library.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.workshop.g37_library.model.AppUser;

public interface AppUserRepository extends CrudRepository <AppUser, Integer> {

}
