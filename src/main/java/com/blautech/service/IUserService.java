package com.blautech.service;

import com.blautech.model.dto.UserDto;
import com.blautech.model.entity.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User save(UserDto user);

    User findById(Integer id);

    void delete(User user);

    boolean existsById(Integer id);
}
