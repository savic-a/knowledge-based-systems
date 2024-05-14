package com.ftn.sbnz.model;

import java.io.Serializable;
import java.util.Date;

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
public class Alarm implements Serializable {

    public enum Level {
        LOW, MEDIUM, HIGH
    };
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Level level;
    private Long clientId;
    private Date executionTime;

    public Alarm(Long id, String desc, Level l, Long client) {
        this.id = id;
        this.description = desc;
        this.clientId = client;
        this.level = l;
        this.executionTime = new Date();
    }
}
