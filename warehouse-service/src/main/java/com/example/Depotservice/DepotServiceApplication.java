package com.example.Depotservice;

import com.example.Depotservice.entities.Depot;
import com.example.Depotservice.entities.Rangee;
import com.example.Depotservice.repositories.DepotRepository;
import com.example.Depotservice.repositories.RangeeRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition
public class DepotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepotServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(DepotRepository depotRepository, RangeeRepository rangeeRepository){
		return args -> {
			/*Rangee rangee1 = new Rangee (null,"rangee1",new Date(),null,5000,0,null);
			Rangee rangee2 = new Rangee(null,"rangee2",new Date(),null,7000,0,null);
			Rangee rangee3 = new Rangee(null,"rangee3",new Date(),null,10000,0,	null);*/
			Rangee rangee1 = new Rangee (null,"rangee1",new Date(),null,5000,0);
			Rangee rangee2 = new Rangee(null,"rangee2",new Date(),null,7000,0);
			Rangee rangee3 = new Rangee(null,"rangee3",new Date(),null,10000,0);


			rangeeRepository.save(rangee1);
			rangeeRepository.save(rangee2);
			rangeeRepository.save(rangee3);

			rangeeRepository.findAll().forEach(c->{
				System.out.println(c.toString());
			});


			Depot depot1=  new  Depot(null,"depot1",20000,0,null,new Date());
				depot1.getRangees().add(rangee1);
			Depot depot2=  new Depot(null,"depot2",30000,0,null,new Date());
				depot2.getRangees().add(rangee2);
			Depot depot3=  new Depot(null,"depot3",50000,0,null,new Date());
				depot3.getRangees().add(rangee3);
			depotRepository.save(depot1);
			depotRepository.save(depot2);
			depotRepository.save(depot3);


			depotRepository.findAll().forEach(c->{
				System.out.println(c.toString());
			});


		};
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};


	}
}
