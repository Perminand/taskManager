package ru.perminov.service.task;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import ru.perminov.dto.task.ParamTaskDto;
import ru.perminov.dto.task.TaskDto;
import ru.perminov.dto.task.TaskDtoOut;

import java.util.List;

public interface TaskService {
    TaskDtoOut create(TaskDto taskDto);

    TaskDtoOut update(@Valid TaskDto dto, Long id);

    TaskDtoOut getById(@Positive Long id);

    void deleteById(@Positive Long id);

    List<TaskDtoOut> getAll(ParamTaskDto paramTaskDto);
}
