package com.ftn.sbnz.model;

import java.io.Serializable;
import java.security.Timestamp;

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
public class Transaction implements Serializable {

    public enum Type {
        INCOME, EXPENDITURE
    };
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private double value;
    private Timestamp date;
    private Type type;
    private Long clientId;
}
