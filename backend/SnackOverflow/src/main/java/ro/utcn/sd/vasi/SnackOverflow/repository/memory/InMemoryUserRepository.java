package ro.utcn.sd.vasi.SnackOverflow.repository.memory;

import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.UserRepository;

import java.util.Optional;

public class InMemoryUserRepository extends InMemoryGeneralRepository<User> implements UserRepository {
    public InMemoryUserRepository(InMemoryRepositoryFactory factory) {
        super(factory);
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return this.findAll().stream().filter(x -> x.getUsername().equals(username)).findAny();
    }
}
