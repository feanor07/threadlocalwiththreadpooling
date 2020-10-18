package com.dilek.alptug.threadlocalwiththreadpooling.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeveloperTaskAssignmentDTO {
    private String name;
    private Long id;
    private int taskCount;
}
