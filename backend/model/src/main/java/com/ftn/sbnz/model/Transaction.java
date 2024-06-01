package com.ftn.sbnz.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

    public enum Type {
        INCOME, OUTCOME
    };

    public enum Category {
        SHOPPING,
        FUN,
        GROCERIES,
        RENT,
        UTILITES
    };
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double value;
    private Timestamp date;
    private Type type;
    private Long clientId;
    private Category category;
    private Boolean isProcessed;

    public Transaction(Long id, double value, Timestamp date, Type type, Long clientId, Category category) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.type = type;
        this.clientId = clientId;
        this.category = category;
        this.isProcessed = false;
    }
}
