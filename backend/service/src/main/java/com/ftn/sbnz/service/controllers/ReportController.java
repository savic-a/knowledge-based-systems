package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.dto.ReportDTO;
import com.ftn.sbnz.model.Report;
import com.ftn.sbnz.service.services.implementations.ReportService;
import com.ftn.sbnz.service.services.interfaces.IService;

@RestController
@RequestMapping("/report")
public class ReportController {
    private IService<Report> service;

    @Autowired
    private ReportService rService;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Report>> getAll() {
        List<Report> reports = this.service.getAll();
        return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<Report>> getReportForClient(@PathVariable Long clientId) {
        List<Report> reports = this.rService.getReportsByClientId(clientId);
        return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<Report> addReport(@PathVariable Long clientId, @RequestBody ReportDTO request) {
        Report reports = this.rService.addReport(clientId, request);
        return new ResponseEntity<Report>(reports, HttpStatus.OK);
    }
}
