package com.ftn.sbnz.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.sbnz.model.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{

    @Query("SELECT new com.ftn.sbnz.model.Client(c.id, c.name, c.surname, c.email, c.password, c.flag3, c.flag4) FROM Client c")
    List<Client> findAll();
}
