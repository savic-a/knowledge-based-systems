package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.CreditCard;
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

    @GetMapping("/all")
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {
        List<CreditCard> cards = creditCardService.getAll();
        return new ResponseEntity<List<CreditCard>>(cards, HttpStatus.OK);
    }
}
