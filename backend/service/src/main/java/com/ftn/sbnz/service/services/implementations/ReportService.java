package com.ftn.sbnz.service.services.implementations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.dto.ReportDTO;
import com.ftn.sbnz.model.Report;
import com.ftn.sbnz.service.repositories.ReportRepository;
import com.ftn.sbnz.service.services.interfaces.IService;
import com.ftn.sbnz.singleton.KieSessionService;


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
        List<Report> reports = this.repository.findByClientId(clientId);
        List<Report> kieSessionReports = KieSessionService.getAllReportsByClientId(clientId);
        for (Report report: kieSessionReports) {
            if (!reports.contains(report)) this.repository.save(report);
        }

        return kieSessionReports;
    }

    public Report addReport(Long clientId, ReportDTO dto) {
        Report report = new Report();
        report.setWeekNum(1);
        report.setReason(dto.getReason());
        report.setGenerationDate(Timestamp.valueOf(LocalDateTime.now()));
        report.setClientId(clientId);

        System.out.println("-------------------------");
        KieSession kieSession = KieSessionService.getKieSession();
        kieSession.insert(report);
        kieSession.fireAllRules();
        System.out.println("-----------------------");

        return repository.save(report);
    }
}
