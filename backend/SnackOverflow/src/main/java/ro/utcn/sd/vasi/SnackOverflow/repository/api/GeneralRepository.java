package ro.utcn.sd.vasi.SnackOverflow.repository.api;

import java.util.List;
import java.util.Optional;

public interface GeneralRepository<T> {
    List<T> findAll();

    T save(T element);

    void remove(T element);

    Optional<T> findById(int id);
}
