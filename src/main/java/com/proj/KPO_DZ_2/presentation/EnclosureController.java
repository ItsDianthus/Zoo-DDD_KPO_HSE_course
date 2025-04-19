package com.proj.KPO_DZ_2.presentation;

import com.proj.KPO_DZ_2.domain.model.Enclosure;
import com.proj.KPO_DZ_2.domain.repository.EnclosureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enclosures")
public class EnclosureController {
    private final EnclosureRepository enclosureRepo;

    public EnclosureController(EnclosureRepository enclosureRepo) {
        this.enclosureRepo = enclosureRepo;
    }

    @GetMapping
    public List<Enclosure> getAll() {
        return enclosureRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enclosure> getById(@PathVariable UUID id) {
        return enclosureRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Enclosure> create(@RequestBody Enclosure enclosure) {
        Enclosure saved = enclosureRepo.save(enclosure);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        enclosureRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/clean")
    public ResponseEntity<Enclosure> clean(@PathVariable UUID id) {
        return enclosureRepo.findById(id)
                .map(e -> {
                    e.clean();
                    enclosureRepo.save(e);
                    return ResponseEntity.ok(e);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/addAnimal")
    public ResponseEntity<Enclosure> addAnimal(@PathVariable UUID id) {
        return enclosureRepo.findById(id)
                .map(e -> {
                    e.addAnimal();
                    enclosureRepo.save(e);
                    return ResponseEntity.ok(e);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/removeAnimal")
    public ResponseEntity<Enclosure> removeAnimal(@PathVariable UUID id) {
        return enclosureRepo.findById(id)
                .map(e -> {
                    e.removeAnimal();
                    enclosureRepo.save(e);
                    return ResponseEntity.ok(e);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}