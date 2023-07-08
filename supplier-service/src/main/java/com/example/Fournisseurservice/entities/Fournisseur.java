package com.example.Fournisseurservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    String telephone;
    String telephoneFixe;
    String email;
    String adresse;
    String ville;
    Date createdAt;
    Date modifiedAt;
    @ManyToOne
    TypeFournisseur typeFournisseur;
}

