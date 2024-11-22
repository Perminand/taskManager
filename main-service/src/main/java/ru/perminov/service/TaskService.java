package ru.perminov.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import ru.perminov.dto.ParamTaskDto;
import ru.perminov.dto.TaskDto;
import ru.perminov.dto.TaskDtoOut;

import java.util.List;

public interface TaskService {
    TaskDtoOut create(TaskDto taskDto);

    TaskDtoOut update(@Valid TaskDto dto, Long id);

    TaskDtoOut getById(@Positive Long id);

    void deleteById(@Positive Long id);

    List<TaskDtoOut> getAll(ParamTaskDto paramTaskDto);
}
