package com.blautech.model.dao;

import com.blautech.model.entity.Task;
import com.blautech.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskDao extends CrudRepository<Task,Integer> {

    @Query("SELECT t FROM Task t WHERE t.userId = :userId")
    List<Task> findByUser(@Param("userId") User id);
}
