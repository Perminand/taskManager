package ru.perminov.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 50, message = "Длина строки должна быть до 50 символов")
    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusTask statusTask;

    @Enumerated(EnumType.STRING)
    private PriorityTask priorityTask;

    private String commit;

    private Long ownerId;

    private Long executorId;
}
