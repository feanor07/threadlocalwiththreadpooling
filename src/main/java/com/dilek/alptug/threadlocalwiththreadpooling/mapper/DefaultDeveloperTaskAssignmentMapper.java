package com.dilek.alptug.threadlocalwiththreadpooling.mapper;

import com.dilek.alptug.threadlocalwiththreadpooling.model.Developer;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskAssignmentDTO;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskCountBreakdown;
import com.dilek.alptug.threadlocalwiththreadpooling.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("not-optimized-developerTaskAssignment-mapper")
public class DefaultDeveloperTaskAssignmentMapper implements DeveloperTaskAssignmentMapper {
    private final DeveloperRepository developerRepository;

    public DefaultDeveloperTaskAssignmentMapper(@Autowired DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public DeveloperTaskAssignmentDTO mapDeveloper(Developer developer) {
        Optional<DeveloperTaskCountBreakdown> breakdown = developerRepository.getDeveloperTaskBreakdown().stream().filter(
                b -> b.getDeveloperId().equals(developer.getId())).findFirst();

        return new DeveloperTaskAssignmentDTO(
                developer.getFullName(), developer.getId(), breakdown.orElseGet(DeveloperTaskCountBreakdown::new).getTaskCount());
    }
}
