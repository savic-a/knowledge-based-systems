package com.ftn.sbnz.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.ClientFivePurchases;

public interface ClientFivePurchasesRepository extends JpaRepository<ClientFivePurchases, Long> {

    List<ClientFivePurchases> findByClientId(Long clientId);
    
}
