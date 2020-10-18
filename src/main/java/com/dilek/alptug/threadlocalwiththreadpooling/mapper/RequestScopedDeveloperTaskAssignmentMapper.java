package com.dilek.alptug.threadlocalwiththreadpooling.mapper;

import com.dilek.alptug.threadlocalwiththreadpooling.model.Developer;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskAssignmentDTO;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskCountBreakdown;
import com.dilek.alptug.threadlocalwiththreadpooling.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

@Component
@Qualifier("request-scoped-developerTaskAssignment-mapper")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedDeveloperTaskAssignmentMapper implements DeveloperTaskAssignmentMapper {

    private List<DeveloperTaskCountBreakdown> allDevelopersTaskBreakdown;

    private final DeveloperRepository developerRepository;

    public RequestScopedDeveloperTaskAssignmentMapper(@Autowired DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public DeveloperTaskAssignmentDTO mapDeveloper(Developer developer) {
        Optional<DeveloperTaskCountBreakdown> breakdown = getAllDevelopersTaskBreakdown().stream().filter(
                b -> b.getDeveloperId().equals(developer.getId())).findFirst();

        return new DeveloperTaskAssignmentDTO(
                developer.getFullName(), developer.getId(), breakdown.orElseGet(DeveloperTaskCountBreakdown::new).getTaskCount());
    }

    private List<DeveloperTaskCountBreakdown> getAllDevelopersTaskBreakdown() {
        if (allDevelopersTaskBreakdown == null) {
            allDevelopersTaskBreakdown = developerRepository.getDeveloperTaskBreakdown();
        }

        return allDevelopersTaskBreakdown;
    }
}
