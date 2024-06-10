package com.ftn.sbnz.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ftn.sbnz.enumeration.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Client implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    // flags
    @Transient
    @ElementCollection
    private List<Category> threePurchases;
    @Transient
    @ElementCollection
    private List<Category> fivePurchases;
    private boolean flag3;
    private boolean flag4;

    public Client(Long id, String name, String surname, String email, String password, boolean flag3, boolean flag4) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.flag3 = flag3;
        this.flag4 = flag4;
        this.threePurchases = new ArrayList<Category>();
        this.fivePurchases = new ArrayList<Category>();
    }


    public void addThreePurchasesCategory(Category category) {
        if (!threePurchases.contains(category)) {
            threePurchases.add(category);
        }
    }

    public void addFivePurchasesCategory(Category category) {
        if (!fivePurchases.contains(category)) {
            fivePurchases.add(category);
        }
    }
    
    public boolean hasThreePurchasesCategory(Category category) {
        return threePurchases.contains(category);
    }
    
    public boolean hasFivePurchasesCategory(Category category) {
        return fivePurchases.contains(category);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getUsername() {
        return this.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
