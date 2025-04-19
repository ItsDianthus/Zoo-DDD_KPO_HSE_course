package com.proj.KPO_DZ_2.infrastructure.repository;

import com.proj.KPO_DZ_2.domain.model.Enclosure;
import com.proj.KPO_DZ_2.domain.repository.EnclosureRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@Repository
public class InMemoryEnclosureRepository implements EnclosureRepository {
    private final Map<UUID, Enclosure> store = new ConcurrentHashMap<>();

    @Override
    public Enclosure save(Enclosure enclosure) {
        store.put(enclosure.getId(), enclosure);
        return enclosure;
    }

    @Override
    public void deleteById(UUID id) {
        store.remove(id);
    }

    @Override
    public Optional<Enclosure> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Enclosure> findAll() {
        return new ArrayList<>(store.values());
    }
}