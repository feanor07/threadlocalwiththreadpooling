package com.dilek.alptug.threadlocalwiththreadpooling.controller;

import com.dilek.alptug.threadlocalwiththreadpooling.model.Developer;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskAssignmentDTO;
import com.dilek.alptug.threadlocalwiththreadpooling.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeveloperController {
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

    @PostMapping("/developers/{id}/assignTask")
    public Integer assignTask(@PathVariable("id") long id) {
        return developerService.assignTask(id);
    }

    @PostMapping("/developers/{id}/completeTask")
    public Integer completeTask(@PathVariable("id") long id) {
        return developerService.completeTask(id);
    }
}
