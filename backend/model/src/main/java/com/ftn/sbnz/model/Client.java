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
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
