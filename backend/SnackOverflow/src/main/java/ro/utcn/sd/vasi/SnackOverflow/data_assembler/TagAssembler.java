package ro.utcn.sd.vasi.SnackOverflow.data_assembler;

import ro.utcn.sd.vasi.SnackOverflow.model.Tag;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.TagRepository;

public class TagAssembler extends GeneralAssembler<Tag, TagRepository> implements TagRepository {
    public TagAssembler(TagRepository currRepo, RepositoryFactory repositoryFactory) {
        super(currRepo, repositoryFactory);
    }

    @Override
    protected Tag makeElementComplete(Tag element) {
        return element;
    }
}
