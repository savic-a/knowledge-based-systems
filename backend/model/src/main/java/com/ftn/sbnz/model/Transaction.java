package com.ftn.sbnz.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ftn.sbnz.enumeration.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

    public enum Type {
        INCOME, OUTCOME
    };
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double value;
    private Timestamp date;
    private Type type;
    private Category category;
    private Long clientId;
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

    public Transaction(Long id, double value, Timestamp date, Transaction.Type type, Category category, Long clientId) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.type = type;
        this.category = category;
        this.clientId = clientId;
        this.isProcessed = false;
    }
    
}
