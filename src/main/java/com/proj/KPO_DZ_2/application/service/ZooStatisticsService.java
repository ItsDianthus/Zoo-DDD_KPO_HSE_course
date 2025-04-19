package com.proj.KPO_DZ_2.application.service;


import com.proj.KPO_DZ_2.domain.model.Animal;
import com.proj.KPO_DZ_2.domain.model.Enclosure;
import com.proj.KPO_DZ_2.domain.repository.AnimalRepository;
import com.proj.KPO_DZ_2.domain.repository.EnclosureRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ZooStatisticsService {
    private final AnimalRepository animalRepo;
    private final EnclosureRepository enclosureRepo;

    public ZooStatisticsService(AnimalRepository animalRepo,
                                EnclosureRepository enclosureRepo) {
        this.animalRepo = animalRepo;
        this.enclosureRepo = enclosureRepo;
    }

    public long getTotalAnimals() {
        return animalRepo.findAll().size();
    }

    public List<Enclosure> getFreeEnclosures() {
        return enclosureRepo.findAll().stream()
                .filter(e -> e.getCapacity() - e.getCurrentAnimals() > 0)
                .toList();
    }


    public Map<UUID, Long> getCountPerEnclosure() {
        return animalRepo.findAll().stream()
                .collect(Collectors.groupingBy(Animal::getEnclosureId, Collectors.counting()));
    }
}