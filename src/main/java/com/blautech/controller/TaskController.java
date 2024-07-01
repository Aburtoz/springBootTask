package com.blautech.controller;

import com.blautech.model.dto.TaskDto;
import com.blautech.model.entity.Task;
import com.blautech.model.entity.User;
import com.blautech.model.payload.MensageResponse;
import com.blautech.service.ITaskService;
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
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    @GetMapping("tasks")
    public ResponseEntity<?> getAllTasks(){
        List<Task> getList = taskService.findAll();
        if(getList.isEmpty()){
            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("No data")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MensageResponse.builder()
                        .mensaje("Satisfactory action")
                        .object(getList)
                        .build()
                ,HttpStatus.OK);
    }

    @PostMapping("task")
    public ResponseEntity<?> create(@RequestBody TaskDto taskDto){

        try {

            Task taskSave = taskService.save(taskDto);
            return  new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("Saved successfully")
                            .object(TaskDto.builder()
                                    .id(taskSave.getId())
                                    .titulo(taskSave.getTitulo())
                                    .description(taskSave.getDescription())
                                    .estado(taskSave.getEstado())
                                    .userId(taskSave.getUserId().getId())
                                    .userName(taskSave.getUserId().getUsername())
                                    .nombre(taskSave.getUserId().getNombre())
                                    .pasivo(taskSave.getPasivo())
                                    .createdAt(taskSave.getCreatedAt())
                                    .updatedAt(taskSave.getUpdatedAt())
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

    @PutMapping("task/{id}")
    public ResponseEntity<?> update(@RequestBody TaskDto taskDto, @PathVariable int id) {

        try {

            if(taskService.existsById(id)){

                Task taskUpdate = taskService.save(taskDto);
                return  new ResponseEntity<>(
                        MensageResponse.builder()
                                .mensaje("Saved successfully")
                                .object( TaskDto.builder()
                                        .id(taskUpdate.getId())
                                        .titulo(taskUpdate.getTitulo())
                                        .description(taskUpdate.getDescription())
                                        .estado(taskUpdate.getEstado())
                                        .userId(taskUpdate.getUserId().getId())
                                        .userName(taskUpdate.getUserId().getUsername())
                                        .nombre(taskUpdate.getUserId().getNombre())
                                        .pasivo(taskUpdate.getPasivo())
                                        .createdAt(taskUpdate.getCreatedAt())
                                        .updatedAt(taskUpdate.getUpdatedAt())
                                        .build())
                                .build()
                        ,HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(
                        MensageResponse.builder()
                                .mensaje("task not found")
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

    @DeleteMapping("task/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        try {
            Task task = taskService.findById(id);
            taskService.delete(task);
            return new ResponseEntity<>(task,HttpStatus.NO_CONTENT);
        }catch (DataAccessException ex){

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("task/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Task task = taskService.findById(id);

        if(task == null){

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("Task not found")
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("Task found")
                            .object(TaskDto.builder()
                                    .id(task.getId())
                                    .titulo(task.getTitulo())
                                    .description(task.getDescription())
                                    .estado(task.getEstado())
                                    .userId(task.getUserId().getId())
                                    .userName(task.getUserId().getUsername())
                                    .nombre(task.getUserId().getNombre())
                                    .pasivo(task.getPasivo())
                                    .createdAt(task.getCreatedAt())
                                    .updatedAt(task.getUpdatedAt())
                                    .build())
                            .build()
                    ,HttpStatus.OK);
        }
    }

    @GetMapping("task/user/{idUser}")
    public ResponseEntity<?> findByUser(@PathVariable Integer idUser) {

        User user = userService.findById(idUser);
        List<Task> listTask = taskService.findByUser(user);

        if(listTask.isEmpty()){

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("No data")
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{

            return new ResponseEntity<>(
                    MensageResponse.builder()
                            .mensaje("Task found")
                            .object(listTask)
                            .build()
                    ,HttpStatus.OK);
        }
    }
}
