package com.dilek.alptug.threadlocalwiththreadpooling.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeveloperTaskCountBreakdown {
    private Long developerId;
    private int taskCount;
}
