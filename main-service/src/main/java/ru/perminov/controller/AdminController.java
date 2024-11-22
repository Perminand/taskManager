package ru.perminov.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.perminov.service.TaskService;
import ru.perminov.dto.TaskDto;
import ru.perminov.dto.TaskDtoOut;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TaskService taskService;

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDtoOut create(@RequestBody @Valid TaskDto taskDto) {
        log.info("POST запрос на создание TASK: {}", taskDto);
        return taskService.create(taskDto);
    }

    @PatchMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDtoOut update(@RequestBody @Valid TaskDto dto, @PathVariable Long id) {
        log.info("PATCH запрос на изменение TASK: {}");
        return taskService.update(dto, id);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDtoOut> getAll(@RequestParam(name = "ids", required = false) Set<Long> listIds,
                                   @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                   @RequestParam(name = "size", required = false, defaultValue = "10") @Min(0) Integer size) {
        log.info("GET запрос на получение всех TASK");
        return taskService.getAll(listIds, from, size);
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDtoOut getById(@PathVariable @Positive Long id) {
        log.info("GET запрос на получение TASK с id: {}", id);
        return taskService.getById(id);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable @Positive Long id) {
        log.info("GET запрос на удаление TASK с id: {}", id);
        taskService.deleteById(id);
    }
}
