package com.dilek.alptug.threadlocalwiththreadpooling.controller;

import com.dilek.alptug.threadlocalwiththreadpooling.model.Developer;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskAssignmentDTO;
import com.dilek.alptug.threadlocalwiththreadpooling.service.DeveloperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeveloperController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DeveloperService developerService;

    public DeveloperController(@Autowired DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping("/developers")
    public List<Developer> getAllDevelopers() {
        return developerService.getAllDevelopers();
    }

    @GetMapping("/developers/task-breakdown/not-optimized")
    public List<DeveloperTaskAssignmentDTO> getDeveloperTaskAssignmentsNotOptimized() {
        return developerService.getTaskBreakdownViaNotOptimizedMapper();
    }

    @GetMapping("/developers/task-breakdown/thread-local-optimized")
    public List<DeveloperTaskAssignmentDTO> getDeveloperTaskAssignmentsThreadLocalOptimized() {
        logger.info("Thread with id {} is about to execute the request!", Thread.currentThread().getId());
        return developerService.getTaskBreakdownViaThreadLocalOptimizedMapper();
    }

    @GetMapping("/developers/task-breakdown/servlet-request-listener-optimized")
    public List<DeveloperTaskAssignmentDTO> getDeveloperTaskAssignmentsServletRequestListenerOptimized() {
        logger.info("Thread with id {} is about to execute the request!", Thread.currentThread().getId());
        return developerService.getTaskBreakdownViaServletRequestListenerMapper();
    }

    @GetMapping("/developers/task-breakdown/request-scoped")
    public List<DeveloperTaskAssignmentDTO> getDeveloperTaskAssignmentsRequestScoped() {
        return developerService.getTaskBreakdownViaRequestScopedMapper();
    }

    @PostMapping("/developers/{id}/assignTask")
    public Integer assignTask(@PathVariable("id") long id) {
        return developerService.assignTask(id);
    }

    @PostMapping("/developers/{id}/completeTask")
    public Integer completeTask(@PathVariable("id") long id) {
        return developerService.completeTask(id);
    }
}
