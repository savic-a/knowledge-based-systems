package com.ftn.sbnz.enumeration;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PurchaseType {

    @Enumerated(EnumType.STRING)
    IMPULSIVE("Impulsive"),
    FREQUENT("Frequent");
    
    private final String description;
}
