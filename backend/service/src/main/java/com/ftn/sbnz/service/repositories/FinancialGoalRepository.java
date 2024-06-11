package com.ftn.sbnz.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.FinancialGoal;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Long>{
    
    FinancialGoal findByClientId(Long clientId);
}
