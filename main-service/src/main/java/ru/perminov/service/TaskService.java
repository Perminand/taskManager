package ru.perminov.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import ru.perminov.dto.TaskDto;
import ru.perminov.dto.TaskDtoOut;

import java.util.List;
import java.util.Set;

public interface TaskService {
    TaskDtoOut create(TaskDto taskDto);

    TaskDtoOut update(@Valid TaskDto dto, Long id);

    List<TaskDtoOut> getAll(Set<Long> listIds, Integer from, Integer size);

    TaskDtoOut getById(@Positive Long id);

    void deleteById(@Positive Long id);
}
