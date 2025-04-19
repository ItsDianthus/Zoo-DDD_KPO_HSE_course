package com.proj.KPO_DZ_2.domain.repository;

import com.proj.KPO_DZ_2.domain.model.Enclosure;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnclosureRepository {
    Enclosure save(Enclosure enclosure);
    void deleteById(UUID id);
    Optional<Enclosure> findById(UUID id);
    List<Enclosure> findAll();
}