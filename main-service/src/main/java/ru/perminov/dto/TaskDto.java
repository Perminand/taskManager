package ru.perminov.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.perminov.marker.Create;
import ru.perminov.model.PriorityTask;
import ru.perminov.model.StatusTask;

@Data
@Builder
@AllArgsConstructor
public class TaskDto {

    @NotBlank(groups = Create.class, message = "Имя задачи не может быть пустым")
    private String name;

    private String description;

    private StatusTask statusTask;

    private PriorityTask priorityTask;

    private String commit;
    @NotBlank(groups = Create.class, message = "ownerId не может быть null")
    private Long ownerId;

    private Long executorId;
}
