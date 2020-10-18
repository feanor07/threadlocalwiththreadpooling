package com.dilek.alptug.threadlocalwiththreadpooling.mapper;

import com.dilek.alptug.threadlocalwiththreadpooling.model.Developer;
import com.dilek.alptug.threadlocalwiththreadpooling.model.DeveloperTaskAssignmentDTO;

public interface DeveloperTaskAssignmentMapper {
    DeveloperTaskAssignmentDTO mapDeveloper(Developer developer);
}
