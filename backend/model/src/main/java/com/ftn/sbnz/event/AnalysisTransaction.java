package com.ftn.sbnz.event;

import java.io.Serializable;

import org.kie.api.definition.type.Role;

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
public class AnalysisTransaction implements Serializable {

    public enum FinancialGoalType {
        FINISHED,
        OVERRUN_BUDGET,
        SUDDEN_JUMP
    };
    
    private static final long serialVersionUID = 1L;

    private Long clientId;
    private FinancialGoalType type;
    private boolean flagActivated;
}