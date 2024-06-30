package com.blautech.service.impl;

import com.blautech.model.dao.UserDao;
import com.blautech.model.dto.UserDto;
import com.blautech.model.entity.User;
import com.blautech.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return (List) userDao.findAll();
    }

    @Transactional
    @Override
    public User save(UserDto userDto) {
        User newUser = User.builder()
                            .id(userDto.getId())
                            .username(userDto.getUsername())
                            .password(userDto.getPassword())
                            .createdAt(userDto.getCreatedAt())
                            .pasivo(userDto.getPasivo())
                            .updatedAt(userDto.getUpdatedAt())
                            .build();
        return userDao.save(newUser);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Integer id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean existsById(Integer id) {
        return userDao.existsById(id);
    }
}
