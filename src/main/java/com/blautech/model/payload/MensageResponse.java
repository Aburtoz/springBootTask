package com.blautech.model.payload;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class MensageResponse implements Serializable {

    private String mensaje;
    private Object object;
}
