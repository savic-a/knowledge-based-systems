package com.ftn.sbnz.service.services;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Report;
import com.ftn.sbnz.service.repositories.ReportRepository;


@Service
public class ReportService {
    private final KieContainer kieContainer;

    @Autowired
    public ReportRepository repository;

    @Autowired
    public ReportService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Report> getAll() {
        return this.repository.findAll();
    }
    
}
