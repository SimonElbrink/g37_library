package se.lexicon.workshop.g37_library.data;

import se.lexicon.workshop.g37_library.model.Details;

import java.util.Collection;

public interface DetailsDAO {

    Details create(Details details);
    Details findById(int id);
    Collection<Details> findAll();
    Details update(Details details);
    void delete(int id);

}
