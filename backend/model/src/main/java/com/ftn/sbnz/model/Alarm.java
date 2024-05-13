package com.ftn.sbnz.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Alarm implements Serializable {

    public enum Level {
        LOW, MEDIUM, HIGH
    };
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Level Level;
    private Long clientId;
}
