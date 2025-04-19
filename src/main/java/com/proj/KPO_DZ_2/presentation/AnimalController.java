package com.proj.KPO_DZ_2.presentation;

import com.proj.KPO_DZ_2.application.service.AnimalService;
import com.proj.KPO_DZ_2.domain.model.Animal;
import com.proj.KPO_DZ_2.domain.repository.AnimalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    private final AnimalRepository animalRepo;
    private final AnimalService animalService;


    public AnimalController(AnimalRepository animalRepo, AnimalService animalService) {
        this.animalRepo = animalRepo;
        this.animalService = animalService;
    }

    @GetMapping
    public List<Animal> getAll() {
        return animalRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getById(@PathVariable UUID id) {
        return animalRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        Animal saved = animalRepo.save(animal);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable("id") UUID id) {
        boolean existed = animalService.deleteAnimal(id);
        if (existed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}