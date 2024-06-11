package com.ftn.sbnz.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ftn.sbnz.enumeration.Category;

@Getter
@Setter
@ToString
@NoArgsConstructor
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
    @Transient
    private KieSession kieSession = null;

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

    public Client(Long id, String name, String surname, String email, String password, List<Category> threePurchases,
                  List<Category> fivePurchases, boolean flag3, boolean flag4) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.flag3 = flag3;
        this.flag4 = flag4;
        this.threePurchases = threePurchases;
        this.fivePurchases = fivePurchases;
    }

    private void setSession() {
        KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();

        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
        config.setOption(ClockTypeOption.get("pseudo"));
        KieSession ksession = kc.newKieSession("ksession-forward-1", config);
        this.kieSession = ksession;
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

    // from here starts backward chaining logic
    public void backward(BudgetExceeding obj) {
        if (this.kieSession == null) {
            System.out.println("Setting kie session in backward");
            this.setSession();
        }
        if (obj.getStartTime() == null) {
            obj.setStartTime(firstOfTheMonth());
            obj.setEndTime(addDaysToTimestamp(obj.getStartTime(), 7));
            System.out.println(obj.getStartTime());
            System.out.println(obj.getEndTime());
            this.kieSession.insert(obj);
            this.kieSession.fireAllRules();
        }
        else {
            obj.setStartTime(obj.getEndTime());
            obj.setEndTime(addDaysToTimestamp(obj.getEndTime(), 7));
            System.out.println(obj.getStartTime());
            System.out.println(obj.getEndTime());
            this.kieSession.insert(obj);
            this.kieSession.fireAllRules();
        }
        if (isLastDayOfMonth(obj.getEndTime())) {
            System.out.println("krajjjj");
        }
        else {
            backward(obj);
        }
    }

    private Timestamp firstOfTheMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        LocalDateTime firstMoment = firstDayOfMonth.atStartOfDay();
        return Timestamp.valueOf(firstMoment);
    }

    public static Timestamp addDaysToTimestamp(Timestamp timestamp, int days) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        LocalDateTime newDateTime = localDateTime.plusDays(days);
        // check if date is still in the same month
        if (newDateTime.getMonthValue() != localDateTime.getMonthValue()) {
            int lastDayOfMonth = newDateTime.minusMonths(1).toLocalDate().lengthOfMonth();
            newDateTime = newDateTime.minusMonths(1).withDayOfMonth(lastDayOfMonth);
        }
        return Timestamp.valueOf(newDateTime);
    }

    public static boolean isLastDayOfMonth(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        int maxDayOfMonth = localDateTime.getMonth().length(localDateTime.toLocalDate().isLeapYear());
        return localDateTime.getDayOfMonth() == maxDayOfMonth;
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
