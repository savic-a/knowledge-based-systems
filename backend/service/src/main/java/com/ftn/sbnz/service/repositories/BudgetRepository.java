package com.ftn.sbnz.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.Budget;


public interface BudgetRepository extends JpaRepository<Budget, Long>{
    
}
