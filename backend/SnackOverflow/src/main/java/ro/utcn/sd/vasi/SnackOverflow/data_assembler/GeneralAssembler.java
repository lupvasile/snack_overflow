package ro.utcn.sd.vasi.SnackOverflow.data_assembler;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.GeneralRepository;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class GeneralAssembler<T, R extends GeneralRepository<T>> implements GeneralRepository<T> {
    protected final R currRepo;
    protected final RepositoryFactory repositoryFactory;

    @Override
    public List<T> findAll() {
        List<T> res = currRepo.findAll();
        res.forEach(x -> makeElementComplete(x));
        return res;
    }

    @Override
    public T save(T element) {
        return currRepo.save(element);
    }

    @Override
    public void remove(T element) {
        currRepo.remove(element);
    }

    @Override
    public Optional<T> findById(int id) {
        Optional<T> res = currRepo.findById(id);
        if (res.isPresent()) makeElementComplete(res.get());
        return res;
    }

    protected abstract T makeElementComplete(T element);
}
