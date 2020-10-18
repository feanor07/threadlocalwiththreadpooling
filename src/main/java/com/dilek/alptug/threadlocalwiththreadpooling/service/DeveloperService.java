package com.dilek.alptug.threadlocalwiththreadpooling.service;

import com.dilek.alptug.threadlocalwiththreadpooling.mapper.DeveloperTaskAssignmentMapper;
import com.dilek.alptug.threadlocalwiththreadpooling.model.Developer;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskAssignmentDTO;
import com.dilek.alptug.threadlocalwiththreadpooling.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperTaskAssignmentMapper notOptimizedMapper;
    private final DeveloperTaskAssignmentMapper threadLocalOptimizedMapper;
    private final DeveloperTaskAssignmentMapper servletRequestListenerMapper;

    public DeveloperService(@Autowired DeveloperRepository developerRepository,
                            @Autowired @Qualifier("not-optimized-developerTaskAssignment-mapper") DeveloperTaskAssignmentMapper notOptimizedMapper,
                            @Autowired @Qualifier("thread-local-optimized-developerTaskAssignment-mapper") DeveloperTaskAssignmentMapper threadLocalOptimizedMapper,
                            @Autowired @Qualifier("servlet-request-listener-developerTaskAssignment-mapper") DeveloperTaskAssignmentMapper servletRequestListenerMapper) {
        this.developerRepository = developerRepository;
        this.notOptimizedMapper = notOptimizedMapper;
        this.threadLocalOptimizedMapper = threadLocalOptimizedMapper;
        this.servletRequestListenerMapper = servletRequestListenerMapper;
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.getAllDevelopers();
    }

    public List<DeveloperTaskAssignmentDTO> getTaskBreakdownViaNotOptimizedMapper() {
        return developerRepository.getAllDevelopers().stream().map(notOptimizedMapper::mapDeveloper).collect(Collectors.toList());
    }

    public List<DeveloperTaskAssignmentDTO> getTaskBreakdownViaThreadLocalOptimizedMapper() {
        return developerRepository.getAllDevelopers().stream().map(threadLocalOptimizedMapper::mapDeveloper).collect(Collectors.toList());
    }

    public List<DeveloperTaskAssignmentDTO> getTaskBreakdownViaServletRequestListenerMapper() {
        return developerRepository.getAllDevelopers().stream().map(servletRequestListenerMapper::mapDeveloper).collect(Collectors.toList());
    }

    public Integer assignTask(long developerId) {
        return developerRepository.assignTask(developerId);
    }

    public Integer completeTask(long developerId) {
        return developerRepository.completeTask(developerId);
    }
}
