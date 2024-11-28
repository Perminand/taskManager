package ru.perminov.mapper;

import ru.perminov.dto.task.TaskDto;
import ru.perminov.dto.task.TaskDtoOut;
import ru.perminov.model.Task;

public class TaskMapper {
    public static Task toEntity(TaskDto dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStatusTask(dto.getStatusTask());
        task.setPriorityTask(dto.getPriorityTask());
        task.setCommit(dto.getCommit());
        task.setOwnerId(dto.getOwnerId());
        task.setExecutorId(dto.getExecutorId());
        return task;
    }

    public static TaskDtoOut toDto(Task entity) {
        return TaskDtoOut.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .statusTask(entity.getStatusTask())
                .priorityTask(entity.getPriorityTask())
                .commit(entity.getCommit())
                .ownerId(entity.getOwnerId())
                .executorId(entity.getExecutorId())
                .build();
    }
}
