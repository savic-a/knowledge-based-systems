package com.ftn.sbnz.event;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.ftn.sbnz.enumeration.Category;

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
    private Date executionTime;
    private Type type;
    private Long clientId;
    private Category category;

    public TransactionEvent(Long id, double value, Type type, Long client) {
        this.id = id;
        this.value = value;
        this.clientId = client;
        this.type = type;
        this.executionTime = new Date();
    }
}