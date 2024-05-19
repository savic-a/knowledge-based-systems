package com.ftn.sbnz.event;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

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
@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h30m")
public class TransactionEvent implements Serializable {

    public enum Type {
        INCOME, OUTCOME
    };
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private double value;
    private Timestamp date;
    private Type type;
    private Long clientId;
}