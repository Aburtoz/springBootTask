package com.blautech.service;

import com.blautech.model.dto.TaskDto;
import com.blautech.model.entity.Task;

import java.util.List;

public interface ITaskService {

    List<Task> findAll();

    Task save(TaskDto taskDto);

    Task findById(Integer id);

    void delete(Task task);

    boolean existsById(Integer id);
}
