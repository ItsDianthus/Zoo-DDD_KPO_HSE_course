package com.proj.KPO_DZ_2.application.service;

import com.proj.KPO_DZ_2.domain.event.AnimalMovedEvent;
import com.proj.KPO_DZ_2.domain.event.EventPublisher;
import com.proj.KPO_DZ_2.domain.model.Animal;
import com.proj.KPO_DZ_2.domain.repository.AnimalRepository;
import com.proj.KPO_DZ_2.domain.repository.EnclosureRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnimalTransferService {
    private final AnimalRepository animalRepo;
    private final EnclosureRepository enclosureRepo;
    private final EventPublisher eventPublisher;

    public AnimalTransferService(AnimalRepository animalRepo,
                                 EnclosureRepository enclosureRepo,
                                 EventPublisher eventPublisher) {
        this.animalRepo = animalRepo;
        this.enclosureRepo = enclosureRepo;
        this.eventPublisher = eventPublisher;
    }


    public void transfer(UUID animalId, UUID newEnclosureId) {
        Animal animal = animalRepo.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found"));
        if (!enclosureRepo.findById(newEnclosureId).isPresent()) {
            throw new IllegalArgumentException("Enclosure not found");
        }

        UUID oldEnclosure = animal.getEnclosureId();
        animal.moveTo(newEnclosureId);
        animalRepo.save(animal);

        eventPublisher.publish(new AnimalMovedEvent(animalId, oldEnclosure, newEnclosureId));
    }
}