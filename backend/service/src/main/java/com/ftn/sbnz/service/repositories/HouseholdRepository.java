package com.ftn.sbnz.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.Household;

public interface HouseholdRepository extends JpaRepository<Household, Long> {

}
