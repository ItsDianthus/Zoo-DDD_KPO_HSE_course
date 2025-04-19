package com.proj.KPO_DZ_2.infrastructure.repository;

import com.proj.KPO_DZ_2.domain.model.Animal;
import com.proj.KPO_DZ_2.domain.repository.AnimalRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.UUID;

public class InMemoryAnimalRepository implements AnimalRepository {
    private final Map<UUID, Animal> store = new ConcurrentHashMap<>();

    @Override
    public Animal save(Animal animal) {
        store.put(animal.getId(), animal);
        return animal;
    }

    @Override
    public void deleteById(UUID id) {
        store.remove(id);
    }

    @Override
    public Optional<Animal> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Animal> findAll() {
        return new ArrayList<>(store.values());
    }
}
