package com.ftn.sbnz.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.service.services.rules.ForwardRulesService;

@RestController
@RequestMapping("/forward-1")
public class ForwardRulesController {
    
    private ForwardRulesService forwardService;

    @Autowired
    public ForwardRulesController(ForwardRulesService forwardService) {
        this.forwardService = forwardService;
    }

    @GetMapping("")
    public void fireAllRules() {
        forwardService.fireRules();
    }
}
