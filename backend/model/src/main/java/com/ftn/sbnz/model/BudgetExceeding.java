package com.ftn.sbnz.model;

import java.sql.Timestamp;

import com.ftn.sbnz.enumeration.Category;
import com.ftn.sbnz.enumeration.PurchaseType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BudgetExceeding {
    private Timestamp startTime;
    private Timestamp endTime;
    private Category category;
    private PurchaseType purchaseType;
    private double value;
    private double categoryCost;
    private int count;
    private int criterion;
    private Long clientId;

    public BudgetExceeding(Long clientId) {
        this.startTime = null;
        this.endTime = null;
        this.category = null;
        this.purchaseType = null;
        this.value = 0.0;
        this.criterion = 0;
        this.count = 0;
        this.categoryCost = 0.0;
        this.clientId = clientId;
    }
}
