package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.Report;
import com.ftn.sbnz.service.services.implementations.ReportService;
import com.ftn.sbnz.service.services.interfaces.IReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
    private IReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Report>> getAll() {
        List<Report> reports = this.service.getAll();
        return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
    }
}
