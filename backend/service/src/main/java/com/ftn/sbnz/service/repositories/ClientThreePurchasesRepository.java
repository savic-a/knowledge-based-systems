package com.ftn.sbnz.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.ClientThreePurchases;

public interface ClientThreePurchasesRepository extends JpaRepository<ClientThreePurchases, Long>{

    List<ClientThreePurchases> findByClientId(Long clientId);
    
}
