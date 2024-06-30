package com.blautech.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class TaskDto implements Serializable {

    private int id;
    private String titulo;
    private String description;
    private int estado;
    private int userId;
    private String userName;
    private String nombre;
    private Boolean pasivo;
    private Date createdAt;
    private Date updatedAt;

}
