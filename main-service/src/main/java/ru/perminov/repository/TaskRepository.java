package ru.perminov.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.perminov.model.Task;

import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.ownerId in(:ownerIds) AND t.executorId in(:listExecutor)")
    Page<Task> findAllByOwnerIdsPageable(Set<Long> ownerIds, Set<Long> listExecutor, Pageable pageable);
}
