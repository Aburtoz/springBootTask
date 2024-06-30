package com.blautech.model.dao;

import com.blautech.model.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<Task,Integer> {
}
