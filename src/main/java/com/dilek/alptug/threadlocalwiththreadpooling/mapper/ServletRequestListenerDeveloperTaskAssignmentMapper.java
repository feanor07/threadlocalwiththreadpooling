package com.dilek.alptug.threadlocalwiththreadpooling.mapper;

import com.dilek.alptug.threadlocalwiththreadpooling.model.Developer;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskAssignmentDTO;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskCountBreakdown;
import com.dilek.alptug.threadlocalwiththreadpooling.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("servlet-request-listener-developerTaskAssignment-mapper")
public class ServletRequestListenerDeveloperTaskAssignmentMapper implements DeveloperTaskAssignmentMapper, ServletRequestListener {
    private static final ThreadLocal<List<DeveloperTaskCountBreakdown>> DEVELOPER_TASK_ASSIGNMENTS = new ThreadLocal<>();

    private final DeveloperRepository developerRepository;

    public ServletRequestListenerDeveloperTaskAssignmentMapper(@Autowired DeveloperRepository developerRepository) {
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
        List<DeveloperTaskCountBreakdown> allDevelopersTaskBreakdown = DEVELOPER_TASK_ASSIGNMENTS.get();

        if (allDevelopersTaskBreakdown == null) {
            allDevelopersTaskBreakdown = developerRepository.getDeveloperTaskBreakdown();
            DEVELOPER_TASK_ASSIGNMENTS.set(allDevelopersTaskBreakdown);
        }

        return allDevelopersTaskBreakdown;
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) { }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        DEVELOPER_TASK_ASSIGNMENTS.remove();
    }
}
