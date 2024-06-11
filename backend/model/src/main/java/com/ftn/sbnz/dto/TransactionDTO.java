package com.ftn.sbnz.dto;

import com.ftn.sbnz.enumeration.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Double value;
    private int type;
    private String category;

    public Category convertToCategory() {
        switch (this.category) {
            case "FOOD":
                return Category.FOOD;
            case "ENTERTAINMENT":
                return Category.ENTERTAINMENT;
            case "SHOPPING":
                return Category.SHOPPING;
            case "OUTINGS":
                return Category.OUTINGS;
            case "HEALTH_AND_CARE":
                return Category.HEALTH_AND_CARE;
            case "PRIVATE":
                return Category.PRIVATE;
        }
        return null;
    }
}
