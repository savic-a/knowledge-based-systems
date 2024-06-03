package com.ftn.sbnz.service.services.implementations;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Report;
import com.ftn.sbnz.service.repositories.ReportRepository;
import com.ftn.sbnz.service.services.interfaces.IReportService;


@Service
public class ReportService implements IReportService{
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
