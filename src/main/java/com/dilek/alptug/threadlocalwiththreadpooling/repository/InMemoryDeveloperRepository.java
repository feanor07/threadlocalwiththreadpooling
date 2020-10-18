package com.dilek.alptug.threadlocalwiththreadpooling.repository;

import com.dilek.alptug.threadlocalwiththreadpooling.model.Developer;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskCountBreakdown;
import com.dilek.alptug.threadlocalwiththreadpooling.model.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryDeveloperRepository implements DeveloperRepository {
    private static final int INITIAL_DEVELOPER_TASK_COUNT = 5;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final List<Developer> DEVELOPERS = Arrays.asList(new Developer(1L, "Alptug", "Dilek", Title.FULLSTACK_DEVELOPER),
            new Developer(2L, "John", "Doe", Title.BACKEND_DEVELOPER),
            new Developer(3L, "Jane", "Doe", Title.FRONTEND_DEVELOPER));

    private static final Map<Long, Integer> DEVELOPER_TASK_COUNT_MAP = new ConcurrentHashMap<>(
            DEVELOPERS.stream().collect(Collectors.toMap((developer) -> developer.getId(), (developer) -> INITIAL_DEVELOPER_TASK_COUNT)));

    @Override
    public List<DeveloperTaskCountBreakdown> getDeveloperTaskBreakdown() {
        logger.info("Fetching task breakdown for all developers!");

        return DEVELOPER_TASK_COUNT_MAP.entrySet().stream().map((entry) -> new DeveloperTaskCountBreakdown(
                entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return DEVELOPERS;
    }

    @Override
    public Integer assignTask(Long developerId) {
        return addToTaskCount(1, developerId);
    }

    @Override
    public Integer completeTask(Long developerId) {
        return addToTaskCount(-1, developerId);
    }

    private Integer addToTaskCount(int value, Long developerId) {
        int newTaskCount = DEVELOPER_TASK_COUNT_MAP.getOrDefault(developerId, 0) + value;
        DEVELOPER_TASK_COUNT_MAP.put(developerId, newTaskCount);

        return newTaskCount;
    }
}
