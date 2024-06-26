package com.ftn.sbnz.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.Report;


public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByClientId(Long clientId);
}
