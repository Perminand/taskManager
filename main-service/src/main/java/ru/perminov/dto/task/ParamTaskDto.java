package ru.perminov.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
public class ParamTaskDto {
    private Set<Long> listOwner;
    private Set<Long> listExecutor;
    private Integer from;
    private Integer size;
}
