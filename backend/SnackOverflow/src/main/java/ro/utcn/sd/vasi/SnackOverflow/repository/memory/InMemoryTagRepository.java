package ro.utcn.sd.vasi.SnackOverflow.repository.memory;

import ro.utcn.sd.vasi.SnackOverflow.model.Tag;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.TagRepository;

public class InMemoryTagRepository extends InMemoryGeneralRepository<Tag> implements TagRepository {
    public InMemoryTagRepository(InMemoryRepositoryFactory factory) {
        super(factory);
    }
}
