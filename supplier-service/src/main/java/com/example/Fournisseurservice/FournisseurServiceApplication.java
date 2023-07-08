package com.example.Fournisseurservice;

import com.example.Fournisseurservice.entities.Fournisseur;
import com.example.Fournisseurservice.entities.TypeFournisseur;
import com.example.Fournisseurservice.repositories.FournisseurRepository;
import com.example.Fournisseurservice.repositories.TypeFsrRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;

@SpringBootApplication
public class FournisseurServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FournisseurServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(FournisseurRepository fournisseurRepository, TypeFsrRepository typeFsrRepository){
		return args -> {
			fournisseurRepository.save(new Fournisseur(null,"central laitier","0660345298","0660345298","centrale@live.fr","rue 22 hay L1","casablanca",new Date(),null,null));
			fournisseurRepository.save(new Fournisseur(null,"iphone store","0660350298","0660345298","iphone@live.fr","rue 27 hay L7","rabat",new Date(),null,null));
			fournisseurRepository.save(new Fournisseur(null,"huawei store","0660330298","0660345298","huawei@live.fr","rue 30 hay L10","marrakech",new Date(),null,null));

			fournisseurRepository.findAll().forEach(c->{
				System.out.println(c.toString());
			});

			typeFsrRepository.save(new TypeFournisseur(null,"importateur",new Date(),null));
			typeFsrRepository.save(new TypeFournisseur(null,"transformateur",new Date(),null));
			typeFsrRepository.save(new TypeFournisseur(null,"livreur ",new Date(),null));
			typeFsrRepository.findAll().forEach(c->{
				System.out.println(c.toString());
			});
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};


	}
}
