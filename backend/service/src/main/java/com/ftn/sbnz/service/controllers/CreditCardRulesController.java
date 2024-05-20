package com.ftn.sbnz.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.service.services.rules.CreditCardRulesService;

@RestController
@RequestMapping("/credit-card")
public class CreditCardRulesController {
    private CreditCardRulesService creditCardService;

    @Autowired
    public CreditCardRulesController(CreditCardRulesService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("")
    public void fireAllRules() {
        creditCardService.fireRules();
    }
}
