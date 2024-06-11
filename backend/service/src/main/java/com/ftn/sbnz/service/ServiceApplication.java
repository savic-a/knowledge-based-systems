package com.ftn.sbnz.service;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.ftn.sbnz.service.controllers", "com.ftn.sbnz.service.services", "com.ftn.sbnz.service.repositories", "com.ftn.sbnz.service.security.jwt", "com.ftn.sbnz.service.security"})
@SpringBootApplication(scanBasePackages = { "com.ftn.sbnz.model", "com.ftn.sbnz.dto", "com.ftn.sbnz.kjar", "com.ftn.sbnz.service.security"})
@EnableJpaRepositories(basePackages = "com.ftn.sbnz.service.repositories")
@EntityScan(basePackages = {"com.ftn.sbnz.model", "com.ftn.sbnz.dto", "com.ftn.sbnz.service.security"})
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(1000);
		return kContainer;
	}

	@Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setPort(8081);
    }
}
