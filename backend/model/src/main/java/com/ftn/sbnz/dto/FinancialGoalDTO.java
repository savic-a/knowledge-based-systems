package com.ftn.sbnz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialGoalDTO {
    private String name;
    private String description;
    private Double targetValue;
    private String targetDate;
}
