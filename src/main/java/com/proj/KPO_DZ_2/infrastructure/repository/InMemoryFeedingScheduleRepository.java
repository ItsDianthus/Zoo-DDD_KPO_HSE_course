package com.proj.KPO_DZ_2.infrastructure.repository;

import com.proj.KPO_DZ_2.domain.model.FeedingSchedule;
import com.proj.KPO_DZ_2.domain.repository.FeedingScheduleRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@Repository
public class InMemoryFeedingScheduleRepository implements FeedingScheduleRepository {
    private final Map<UUID, FeedingSchedule> store = new ConcurrentHashMap<>();

    @Override
    public FeedingSchedule save(FeedingSchedule schedule) {
        store.put(schedule.getId(), schedule);
        return schedule;
    }

    @Override
    public void deleteById(UUID id) {
        store.remove(id);
    }

    @Override
    public Optional<FeedingSchedule> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<FeedingSchedule> findAll() {
        return new ArrayList<>(store.values());
    }
}