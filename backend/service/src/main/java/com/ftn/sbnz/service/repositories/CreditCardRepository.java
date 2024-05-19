package com.ftn.sbnz.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
    
}
