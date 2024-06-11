package com.ftn.sbnz.service.services.implementations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.dto.ReportDTO;
import com.ftn.sbnz.model.Report;
import com.ftn.sbnz.service.repositories.ReportRepository;
import com.ftn.sbnz.service.services.interfaces.IService;


@Service
public class ReportService implements IService<Report>{
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
    
    public List<Report> getReportsByClientId(Long clientId) {
        return this.repository.findByClientId(clientId);
    }

    public Report addReport(Long clientId, ReportDTO dto) {
        Report report = new Report();
        report.setWeekNum(1);
        report.setReason(dto.getReason());
        report.setGenerationDate(Timestamp.valueOf(LocalDateTime.now()));
        report.setClientId(clientId);

        return repository.save(report);
    }
}
