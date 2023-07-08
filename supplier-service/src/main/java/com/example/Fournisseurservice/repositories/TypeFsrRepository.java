package com.example.Fournisseurservice.repositories;

import com.example.Fournisseurservice.entities.TypeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TypeFsrRepository extends JpaRepository<TypeFournisseur,Long> {

    TypeFournisseur findTypeFournisseurById(Long id);
    TypeFournisseur findTypeFournisseurByNom(String nom);

}
