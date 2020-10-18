package com.dilek.alptug.threadlocalwiththreadpooling.repository;

import com.dilek.alptug.threadlocalwiththreadpooling.model.Developer;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskCountBreakdown;

import java.util.List;

public interface DeveloperRepository {
    List<DeveloperTaskCountBreakdown> getDeveloperTaskBreakdown();

    List<Developer> getAllDevelopers();

    Integer assignTask(Long developerId);

    Integer completeTask(Long developerId);
}
