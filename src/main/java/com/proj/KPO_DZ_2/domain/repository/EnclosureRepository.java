package com.proj.KPO_DZ_2.domain.repository;

import com.proj.KPO_DZ_2.domain.model.Enclosure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnclosureRepository {
    Enclosure save(Enclosure enclosure);
    void deleteById(UUID id);
    Optional<Enclosure> findById(UUID id);
    List<Enclosure> findAll();
}