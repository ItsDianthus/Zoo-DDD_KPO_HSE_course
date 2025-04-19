package com.proj.KPO_DZ_2.domain.repository;

import com.proj.KPO_DZ_2.domain.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnimalRepository {
    Animal save(Animal animal);
    void deleteById(UUID id);
    Optional<Animal> findById(UUID id);
    List<Animal> findAll();
}
