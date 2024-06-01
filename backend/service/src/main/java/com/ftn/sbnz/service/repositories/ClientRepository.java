package com.ftn.sbnz.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
