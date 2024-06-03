package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.drools.core.beliefsystem.abductive.Abducible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.Budget;
import com.ftn.sbnz.service.services.implementations.BudgetService;
import com.ftn.sbnz.service.services.interfaces.IBudgetService;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    private IBudgetService service;

    @Autowired
    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Budget>> getAll() {
        List<Budget> budgets = service.getAll();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }
    
}
