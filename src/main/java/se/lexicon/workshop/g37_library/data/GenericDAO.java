package se.lexicon.workshop.g37_library.data;

import java.util.Collection;

public interface GenericDAO <T,ID>{

    //CRUD

    T create(T t);
    T findById(ID id);
    Collection<T> findAll();
    T update(T t);
    void delete(ID id);


}
