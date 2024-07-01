package com.blautech.controller;
import com.blautech.model.dto.UserDto;
import com.blautech.model.entity.User;
import com.blautech.model.payload.MensageResponse;
import com.blautech.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("users")
    public ResponseEntity<?> getAllUsers() {
        List<User> getList = userService.findAll();
        if(getList.isEmpty()) {

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("No data")
                            .object(null)
                            .build()
                    ,HttpStatus.OK);
        }

        return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("Satisfactory action")
                            .object(getList)
                            .build()
                    ,HttpStatus.OK);


    }

    @PostMapping("user")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {

        try {
            User userSave = userService.save(userDto);
            return  new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("Saved successfully")
                            .object(UserDto.builder()
                                    .id(userSave.getId())
                                    .nombre(userSave.getNombre())
                                    .username(userSave.getUsername())
                                    .password(userSave.getPassword())
                                    .createdAt(userSave.getCreatedAt())
                                    .pasivo(userSave.getPasivo())
                                    .updatedAt(userSave.getUpdatedAt())
                                    .build())
                            .build()
                    ,HttpStatus.CREATED);

        }catch (DataAccessException ex){
            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable int id) {

        try {

            if(userService.existsById(id)){

                User userUpdate = userService.save(userDto);
                return  new ResponseEntity<>(
                        MensageResponse.builder()
                                .mensaje("Saved successfully")
                                .object( UserDto.builder()
                                        .id(userUpdate.getId())
                                        .nombre(userUpdate.getNombre())
                                        .username(userUpdate.getUsername())
                                        .password(userUpdate.getPassword())
                                        .createdAt(userUpdate.getCreatedAt())
                                        .pasivo(userUpdate.getPasivo())
                                        .updatedAt(userUpdate.getUpdatedAt())
                                        .build())
                                .build()
                        ,HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(
                        MensageResponse.builder()
                                .mensaje("User not found")
                                .object(null)
                                .build()
                        ,HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException ex){

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        try {
            User user = userService.findById(id);
            userService.delete(user);
            return new ResponseEntity<>(user,HttpStatus.NO_CONTENT);
        }catch (DataAccessException ex){

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        User user = userService.findById(id);

        if(user == null){

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("User not found")
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("User found")
                            .object(UserDto.builder()
                                    .id(user.getId())
                                    .nombre(user.getNombre())
                                    .username(user.getUsername())
                                    .password(user.getPassword())
                                    .createdAt(user.getCreatedAt())
                                    .pasivo(user.getPasivo())
                                    .updatedAt(user.getUpdatedAt())
                                    .build())
                            .build()
                    ,HttpStatus.OK);
        }
    }
}
