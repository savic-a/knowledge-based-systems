package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.dto.HouseholdDTO;
import com.ftn.sbnz.model.Household;
import com.ftn.sbnz.service.services.implementations.HouseholdService;
import com.ftn.sbnz.service.services.interfaces.IService;

@RestController
@RequestMapping("/household")
public class HouseholdController {
    private IService<Household> service;

    @Autowired
    private HouseholdService hhService;

    public HouseholdController(HouseholdService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Household>> getAll() {
        List<Household> reports = this.service.getAll();
        return new ResponseEntity<List<Household>>(reports, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<HouseholdDTO>> getHouseholdByClientId(@PathVariable Long clientId) {
        List<HouseholdDTO> households = hhService.getHouseholdByClientId(clientId);
        return new ResponseEntity<List<HouseholdDTO>>(households, HttpStatus.OK);
    }

    // @PostMapping("/{clientId}")
    // public ResponseEntity<Household> addHousehold(@PathVariable Long clientId,
    //                                                       @RequestBody HouseholdDTO request) {
    //     Household newHousehold = tService.addHousehold(clientId, request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(newHousehold);
    // }
}
