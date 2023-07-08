package com.example.Depotservice.repositories;

import com.example.Depotservice.entities.Depot;
import com.example.Depotservice.entities.Rangee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RangeeRepository extends JpaRepository<Rangee,Long> {


   void deleteById(Depot depot);
   Rangee findRangeeByNom(String nom);

   Rangee findRangeeById(Long id);



/*
   @Query("select p from Product p where p.category.nom like :kw")
   Collection<Product> findProductByCategoryNom(@Param(value = "kw")String nom);*/


}
