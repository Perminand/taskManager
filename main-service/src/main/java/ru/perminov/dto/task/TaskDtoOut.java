package ru.perminov.dto.task;

import lombok.Builder;
import lombok.Data;
import ru.perminov.model.PriorityTask;
import ru.perminov.model.StatusTask;

@Data
@Builder
public class TaskDtoOut {
    private Long id;

    private String name;

    private String description;

    private StatusTask statusTask;

    private PriorityTask priorityTask;

    private String commit;

    private Long ownerId;

    private Long executorId;
}
