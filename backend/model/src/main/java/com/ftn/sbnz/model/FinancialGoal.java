package com.ftn.sbnz.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;

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
@Table
@Entity
public class FinancialGoal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Timestamp generationDate;
    private double targetValue;
    private Timestamp targetDate;
    private double currentBalance;
    private double startBalance;
    private Long clientId;

    public int calculateNumOfMonths() {
        LocalDateTime dateTime1 = this.generationDate.toLocalDateTime();
        LocalDateTime dateTime2 = this.targetDate.toLocalDateTime();

        Period period = Period.between(dateTime1.toLocalDate(), dateTime2.toLocalDate());
        int months = period.getYears() * 12 + period.getMonths();
        System.out.println("Months between the timestamps: " + months);
        if (months == 0) months = 1;
        return months;
    }
}