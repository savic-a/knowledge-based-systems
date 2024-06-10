package com.ftn.sbnz.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.time.SessionPseudoClock;

import com.ftn.sbnz.enumeration.Category;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
public class Client implements Serializable {

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

    private void setKieSession() {
        KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();

        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
        config.setOption(ClockTypeOption.get("pseudo"));
        KieSession ksession = kc.newKieSession("ksession-cep", config);
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
        if (obj.getStartTime() == null) {
            obj.setStartTime(firstOfTheMonth());
            obj.setEndTime(addDaysToTimestamp(obj.getStartTime(), 7));
            System.out.println(obj.getStartTime());
            System.out.println(obj.getEndTime());
            // TODO: insert budget exceeding object to key session
            setKieSession();
            this.kieSession.insert(obj);
        }
        else {
            obj.setStartTime(obj.getEndTime());
            obj.setEndTime(addDaysToTimestamp(obj.getEndTime(), 7));
            System.out.println(obj.getStartTime());
            System.out.println(obj.getEndTime());
            // TODO: insert budget exceeding object to key session
            this.kieSession.insert(obj);
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
}
