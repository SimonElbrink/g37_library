package se.lexicon.workshop.g37_library.data.dao;

import se.lexicon.workshop.g37_library.model.AppUser;

import java.util.Collection;

public interface AppUserDAO {

    //CRUD

    AppUser findById(int id);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(int id);


}
