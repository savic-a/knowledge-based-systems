package com.ftn.sbnz.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    FOOD("Food"),
    ENTERTAINMENT("Entertainment"),
    SHOPPING("Shopping"),
    OUTINGS("Outings"),
    HEALTH_AND_CARE("Health and Care"),
    PRIVATE("Private");  // income transaction can have only this category

    private final String description;
}