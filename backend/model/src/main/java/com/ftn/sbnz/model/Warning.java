package com.ftn.sbnz.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Warning implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long clientId;
    private String level;
    private String message;
}
