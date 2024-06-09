package com.ftn.sbnz.enumeration;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    
    @Enumerated(EnumType.STRING)
    FOOD("Food"),
    ENTERTAINMENT("Entertainment"),
    SHOPPING("Shopping"),
    OUTINGS("Outings"),
    HEALTH_AND_CARE("Health and Care"),
    PRIVATE("Private");  // income transaction can have only this category

    private final String description;
}
