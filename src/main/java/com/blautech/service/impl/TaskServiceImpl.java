package com.blautech.service.impl;

import com.blautech.model.dao.TaskDao;
import com.blautech.model.dao.UserDao;
import com.blautech.model.dto.TaskDto;
import com.blautech.model.dto.UserDto;
import com.blautech.model.entity.Task;
import com.blautech.model.entity.User;
import com.blautech.service.ITaskService;
import com.blautech.service.IUserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public List<Task> findAll() {
        return (List) taskDao.findAll();
    }

    @Transactional
    @Override
    public Task save(TaskDto taskDto) {

        User user = userDao.findById(taskDto.getUserId()).orElse(null);

        Task newTask = Task.builder()
                .id(taskDto.getId())
                .titulo(taskDto.getTitulo())
                .description(taskDto.getDescription())
                .estado(taskDto.getEstado())
                .userId(user)
                .pasivo(taskDto.getPasivo())
                .createdAt(taskDto.getCreatedAt())
                .updatedAt(taskDto.getUpdatedAt())
                .build();
        return taskDao.save(newTask);
    }

    @Transactional(readOnly = true)
    @Override
    public Task findById(Integer id) {
        return taskDao.findById(id).orElse(null);
    }

    @Override
    public List<Task> findByUser(User user) {
        return taskDao.findByUser(user);
    }


    @Override
    public void delete(Task task) {
     taskDao.delete(task);
    }

    @Override
    public boolean existsById(Integer id) {
        return taskDao.existsById(id);
    }
}
