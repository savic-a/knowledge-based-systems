package com.ftn.sbnz.service.services.interfaces;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ftn.sbnz.model.Client;

public interface IClientService extends IService {
    public Client findByEmail(String email) throws UsernameNotFoundException;
} 
