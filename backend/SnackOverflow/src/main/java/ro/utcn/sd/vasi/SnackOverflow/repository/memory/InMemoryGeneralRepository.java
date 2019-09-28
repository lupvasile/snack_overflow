package ro.utcn.sd.vasi.SnackOverflow.repository.memory;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.vasi.SnackOverflow.model.HasIntId;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.GeneralRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class InMemoryGeneralRepository<T extends HasIntId> implements GeneralRepository<T> {
    protected final InMemoryRepositoryFactory factory;
    private final Map<Integer, T> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public T save(T element) {
        if (element.getId() == null) {
            element.setId(currentId.incrementAndGet());
        }

        data.put(element.getId(), element);
        return element;
    }

    @Override
    public void remove(T element) {
        data.remove(element.getId());
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }
}
