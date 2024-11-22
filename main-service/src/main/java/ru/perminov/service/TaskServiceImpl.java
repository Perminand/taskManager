package ru.perminov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.perminov.dto.TaskDto;
import ru.perminov.dto.TaskDtoOut;
import ru.perminov.exceptions.errors.EntityNotFoundException;
import ru.perminov.mapper.TaskMapper;
import ru.perminov.model.Task;
import ru.perminov.repository.TaskRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public TaskDtoOut create(TaskDto taskDto) {
        Task task = TaskMapper.toEntity(taskDto);
        taskRepository.save(task);
        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDtoOut update(TaskDto dto, Long id) {
        Task task = getTask(id);

        if(dto.getName() != null) {
            task.setName(dto.getName());
        }

        if(dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }

        if(dto.getStatusTask() != null) {
            task.setStatusTask(dto.getStatusTask());
        }

        if(dto.getPriorityTask() != null) {
            task.setPriorityTask(dto.getPriorityTask());
        }

        if(dto.getCommit() != null) {
            task.setCommit(dto.getCommit());
        }

        if(dto.getOwnerId() != null) {
            task.setOwnerId(dto.getOwnerId());
        }
        taskRepository.save(task);
        return TaskMapper.toDto(task);

    }

    private Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Нет TASK с id: " + id));
    }

    @Override
    public List<TaskDtoOut> getAll(Set<Long> listIds, Integer from, Integer size) {
        Sort sortById = Sort.by(Sort.Direction.ASC, "id");
        int startPage = from > 0 ? (from / size) : 0;
        Pageable pageable = PageRequest.of(startPage, size, sortById);
        Page<Task> users;

        if (listIds != null) {
            if (listIds.isEmpty()) {
                return Collections.emptyList();
            }
            users = taskRepository.findAllByIdsPageable(listIds, pageable);
        } else {
            users = taskRepository.findAll(pageable);
        }

        return taskRepository.findAll().stream().map(TaskMapper::toDto).toList();
    }

    @Override
    public TaskDtoOut getById(Long id) {
        return TaskMapper.toDto(getTask(id));
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
