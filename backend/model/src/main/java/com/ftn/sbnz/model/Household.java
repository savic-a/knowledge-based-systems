package com.ftn.sbnz.model;

import java.io.Serializable;

import org.kie.api.definition.type.Position;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Household implements Serializable {

    private static final long serialVersionUID = 1L;
    @Position(0)
    private Long firstPerson;
    @Position(1)
    private Long secondPerson;
}

