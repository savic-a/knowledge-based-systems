package com.ftn.sbnz.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.service.services.ReportsRulesService;

@RestController
@RequestMapping("/reports")
public class ReportsRulesController {
    private ReportsRulesService reportsService;

    @Autowired
    public ReportsRulesController(ReportsRulesService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping("")
    public void fireAllRules() {
        reportsService.fireRules();
    }
}
