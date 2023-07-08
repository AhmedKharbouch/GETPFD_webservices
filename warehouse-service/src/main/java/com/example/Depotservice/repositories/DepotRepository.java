package com.example.Depotservice.repositories;

import com.example.Depotservice.entities.Depot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepotRepository extends JpaRepository<Depot,Long> {

   Depot findDepotById(Long id);
   Depot findDepotByNom(String Nom);


}
