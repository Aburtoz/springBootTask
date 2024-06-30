package com.blautech.model.dto;
import lombok.*;
import java.io.Serializable;
import java.util.Date;


@Data
@ToString
@Builder
public class UserDto implements Serializable {

    private int id;
    private String username;
    private String password;
    private String nombre;
    private Boolean pasivo;
    private Date createdAt;
    private Date updatedAt;

}
