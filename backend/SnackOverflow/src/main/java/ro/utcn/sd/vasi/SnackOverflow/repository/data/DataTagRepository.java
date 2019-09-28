package ro.utcn.sd.vasi.SnackOverflow.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.vasi.SnackOverflow.model.Tag;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.TagRepository;

public interface DataTagRepository extends Repository<Tag, Integer>, TagRepository {
    void delete(Tag tag);

    @Override
    default void remove(Tag tag) {
        delete(tag);
    }
}
