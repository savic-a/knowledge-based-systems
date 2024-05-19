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
public class Budget implements Serializable {
    public enum Period {
        WEEK, MONTH
    };
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private double value;
    private Period period;
    private Long clientId;
}