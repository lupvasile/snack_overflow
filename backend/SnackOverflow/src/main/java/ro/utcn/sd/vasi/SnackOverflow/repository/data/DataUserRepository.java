package ro.utcn.sd.vasi.SnackOverflow.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.UserRepository;

public interface DataUserRepository extends Repository<User, Integer>, UserRepository {
    void delete(User element);

    @Override
    default void remove(User element) {
        delete(element);
    }
}
