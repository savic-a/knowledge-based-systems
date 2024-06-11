package com.ftn.sbnz.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.Household;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
    List<Household> findByFirstPerson(Long clientId);
}
