package ru.perminov.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.perminov.dto.task.ParamTaskDto;
import ru.perminov.dto.task.TaskDto;
import ru.perminov.dto.task.TaskDtoOut;
import ru.perminov.exceptions.errors.EntityNotFoundException;
import ru.perminov.mapper.TaskMapper;
import ru.perminov.model.Task;
import ru.perminov.repository.TaskRepository;

import java.util.HashSet;
import java.util.List;

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

    @Override
    public List<TaskDtoOut> getAll(ParamTaskDto dto) {
        Sort sortById = Sort.by(Sort.Direction.ASC, "id");
        int startPage = dto.getFrom() > 0 ? (dto.getFrom() / dto.getSize()) : 0;
        Pageable pageable = PageRequest.of(startPage, dto.getSize(), sortById);


        if (dto.getListOwner() == null) {
            dto.setListOwner(new HashSet<>());
        }

        if (dto.getListExecutor() == null) {
            dto.setListExecutor(new HashSet<>());
        }


        return taskRepository.findAllByOwnerIdsPageable(dto.getListOwner(), dto.getListExecutor(), pageable)
                .stream()
                .map(TaskMapper::toDto)
                .toList();
    }

    @Override
    public TaskDtoOut getById(Long id) {
        return TaskMapper.toDto(getTask(id));
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    private Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Нет TASK с id: " + id));
    }
}
