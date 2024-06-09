package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.FinancialGoal;
import com.ftn.sbnz.service.services.implementations.FinancialGoalService;
import com.ftn.sbnz.service.services.interfaces.IService;

@RestController
@RequestMapping("/financial-goal")
public class FinancialGoalController {
    private IService<FinancialGoal> service;

    @Autowired
    public FinancialGoalController(FinancialGoalService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<FinancialGoal>> getAll() {
        List<FinancialGoal> goals = this.service.getAll();
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }
}
