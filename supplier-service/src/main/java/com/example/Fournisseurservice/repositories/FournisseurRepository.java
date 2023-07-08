package com.example.Fournisseurservice.repositories;

import com.example.Fournisseurservice.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {

   Fournisseur findFournisseurById(Long id);
    Fournisseur findFournisseurByNom(String nom);

    @Query("select c from Fournisseur c where c.nom like :kw")
    Collection<Fournisseur> findFournisseurByName(@Param(value = "kw")String nom);
}
