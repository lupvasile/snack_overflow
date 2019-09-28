package ro.utcn.sd.vasi.SnackOverflow.repository.api;

import ro.utcn.sd.vasi.SnackOverflow.model.User;

import java.util.Optional;

public interface UserRepository extends GeneralRepository<User> {
    Optional<User> findByUsername(String username);
}
