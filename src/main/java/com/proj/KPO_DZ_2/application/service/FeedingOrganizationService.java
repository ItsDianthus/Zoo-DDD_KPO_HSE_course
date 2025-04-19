package com.proj.KPO_DZ_2.application.service;

import com.proj.KPO_DZ_2.domain.event.EventPublisher;
import com.proj.KPO_DZ_2.domain.event.FeedingTimeEvent;
import com.proj.KPO_DZ_2.domain.model.FeedingSchedule;
import com.proj.KPO_DZ_2.domain.repository.FeedingScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Service
public class FeedingOrganizationService {
    private final FeedingScheduleRepository scheduleRepo;
    private final EventPublisher eventPublisher;

    public FeedingOrganizationService(FeedingScheduleRepository scheduleRepo,
                                      EventPublisher eventPublisher) {
        this.scheduleRepo = scheduleRepo;
        this.eventPublisher = eventPublisher;
    }

    public List<FeedingSchedule> getFeedingsAt(LocalDateTime time) {
        return scheduleRepo.findAll().stream()
                .filter(s -> !s.isCompleted()
                        && Math.abs(s.getFeedingTime().until(time, java.time.temporal.ChronoUnit.MINUTES)) <= 5)
                .toList();
    }


    public FeedingSchedule scheduleFeeding(FeedingSchedule schedule) {
        FeedingSchedule saved = scheduleRepo.save(schedule);
        return saved;
    }

    public void completeFeeding(UUID scheduleId) {
        FeedingSchedule schedule = scheduleRepo.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        schedule.markCompleted();
        scheduleRepo.save(schedule);

        eventPublisher.publish(new FeedingTimeEvent(scheduleId, LocalDateTime.now()));
    }
}