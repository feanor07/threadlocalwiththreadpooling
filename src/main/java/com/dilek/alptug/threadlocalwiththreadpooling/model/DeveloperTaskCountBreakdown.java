package com.dilek.alptug.threadlocalwiththreadpooling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperTaskCountBreakdown {
    private Long developerId;
    private int taskCount;
}
