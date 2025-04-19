package com.proj.KPO_DZ_2.application.service;

import com.proj.KPO_DZ_2.domain.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public boolean deleteAnimal(UUID id) {
        if (animalRepository.findById(id).isEmpty()) {
            return false;
        }
        animalRepository.deleteById(id);
        return true;
    }

}