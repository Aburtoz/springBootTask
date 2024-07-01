package com.blautech.service;

import com.blautech.model.dto.TaskDto;
import com.blautech.model.entity.Task;
import com.blautech.model.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITaskService {

    List<Task> findAll();

    Task save(TaskDto taskDto);

    Task findById(Integer id);

    List<Task> findByUser(User user);



    void delete(Task task);

    boolean existsById(Integer id);
}
