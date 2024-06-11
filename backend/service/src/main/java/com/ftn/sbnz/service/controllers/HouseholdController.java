package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.Household;
import com.ftn.sbnz.service.services.implementations.HouseholdService;
import com.ftn.sbnz.service.services.interfaces.IService;

@RestController
@RequestMapping("/household")
public class HouseholdController {
    private IService<Household> service;

    public HouseholdController(HouseholdService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Household>> getAll() {
        List<Household> reports = this.service.getAll();
        return new ResponseEntity<List<Household>>(reports, HttpStatus.OK);
    }
}
