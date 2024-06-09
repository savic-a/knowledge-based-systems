package com.ftn.sbnz.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.service.services.rules.CepRulesService;

@RestController
@RequestMapping("/cep")
public class CepRulesController {
    private CepRulesService creditCardService;

    @Autowired
    public CepRulesController(CepRulesService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("")
    public void fireAllRules() {
        creditCardService.fireRules();
    }
}
