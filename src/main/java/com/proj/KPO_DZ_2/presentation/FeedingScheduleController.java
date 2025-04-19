package com.proj.KPO_DZ_2.presentation;

import com.proj.KPO_DZ_2.application.service.FeedingOrganizationService;
import com.proj.KPO_DZ_2.domain.model.FeedingSchedule;
import com.proj.KPO_DZ_2.domain.repository.FeedingScheduleRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feedings")
public class FeedingScheduleController {
    private final FeedingScheduleRepository scheduleRepo;
    private final FeedingOrganizationService feedingService;

    public FeedingScheduleController(FeedingScheduleRepository scheduleRepo,
                                     FeedingOrganizationService feedingService) {
        this.scheduleRepo = scheduleRepo;
        this.feedingService = feedingService;
    }

    @GetMapping
    public List<FeedingSchedule> getAll() {
        return scheduleRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedingSchedule> getById(@PathVariable UUID id) {
        return scheduleRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FeedingSchedule> create(@RequestBody FeedingSchedule schedule) {
        FeedingSchedule saved = feedingService.scheduleFeeding(schedule);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/at")
    public List<FeedingSchedule> getAt(
            @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
        return feedingService.getFeedingsAt(time);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> complete(@PathVariable UUID id) {
        feedingService.completeFeeding(id);
        return ResponseEntity.noContent().build();
    }
}