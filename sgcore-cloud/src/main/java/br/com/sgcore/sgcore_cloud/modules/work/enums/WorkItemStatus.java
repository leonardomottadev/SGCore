package br.com.sgcore.sgcore_cloud.modules.work.enums;

import java.util.Arrays;
import java.util.Optional;

public enum WorkItemStatus {
    OPEN,
    ASSIGNED,
    IN_PROGRESS,
    COMPLETED,
    CANCELED,
    UNKNOWN;

    public static Optional<WorkItemStatus> fromString(String value) {
        if(value == null) return Optional.empty();

        return Arrays.stream(values())
                .filter(s -> s.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
