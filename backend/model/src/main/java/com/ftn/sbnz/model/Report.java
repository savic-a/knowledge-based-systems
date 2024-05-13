package com.ftn.sbnz.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private int weekNum;
    private String reason;
    private Timestamp generatonDate;
    private Long clientId;
}