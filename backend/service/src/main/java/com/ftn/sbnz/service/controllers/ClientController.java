package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.dto.LoginDTO;
import com.ftn.sbnz.dto.TokenStateDTO;
import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.service.exeception.BadRequestException;
import com.ftn.sbnz.service.repositories.ClientRepository;
import com.ftn.sbnz.service.security.jwt.TokenUtils;
import com.ftn.sbnz.service.services.implementations.ClientService;
import com.ftn.sbnz.service.services.interfaces.IService;

@RestController
@CrossOrigin
@RequestMapping("/client")
public class ClientController {

    private IService<Client> service;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TokenUtils tokenUtils;

    AuthenticationManager authenticationManager;

    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Client>> getAllCreditCards() {
        List<Client> clients = service.getAll();
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }
    
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenStateDTO> login(@RequestBody LoginDTO loginDTO) 
    {
        System.out.println("LOGGGGGGGGGGGGGGGGGGGGGGGGGIIIIIIIIIIIIIIIIIIIIIIIIIIN");
        // Client check = clientRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new BadRequestException("Wrong username or password!"));
        // if(!check.getEmail().equals(loginDTO.getEmail()) || !passwordEncoder.matches(loginDTO.getPassword(), check.getPassword()))
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        // Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        //         loginDTO.getEmail(), loginDTO.getPassword()));

        // SecurityContextHolder.getContext().setAuthentication(authentication);

        // // Create tokens for that user
        // Client user = (Client) authentication.getPrincipal();
        // String access = tokenUtils.generateToken(user.getEmail());
        // int expiresIn = tokenUtils.getExpiredIn();

        return new ResponseEntity<>(new TokenStateDTO("laa", 123), HttpStatus.OK);
    }
}
