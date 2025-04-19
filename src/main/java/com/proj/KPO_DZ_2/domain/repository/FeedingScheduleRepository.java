package com.proj.KPO_DZ_2.domain.repository;

import com.proj.KPO_DZ_2.domain.model.FeedingSchedule;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedingScheduleRepository {
    FeedingSchedule save(FeedingSchedule schedule);
    void deleteById(UUID id);
    Optional<FeedingSchedule> findById(UUID id);
    List<FeedingSchedule> findAll();
}