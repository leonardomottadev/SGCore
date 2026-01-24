package br.com.sgcore.sgcore_cloud.modules.work.enums;

import java.util.Arrays;
import java.util.Optional;

public enum WorkItemPriority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL;

    public static Optional<WorkItemPriority> fromString(String value) {
        if(value == null) return Optional.empty();

        return Arrays.stream(values())
                .filter(s -> s.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
