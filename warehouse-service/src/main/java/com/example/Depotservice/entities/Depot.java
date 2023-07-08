package com.example.Depotservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Transactional
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    int MaxSize;
    int CurrentSize;
    Date modifiedAt;
    Date createdAt;
    //@OneToMany(mappedBy = "depot",fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Rangee> rangees = new ArrayList<>();


    //generate constructor with all fields exept rangees
    public Depot(Long id,String nom, int maxSize, int currentSize, Date modifiedAt, Date createdAt) {
        this.id = id;
        this.nom = nom;
        MaxSize = maxSize;
        CurrentSize = currentSize;
        this.modifiedAt = modifiedAt;
        this.createdAt = createdAt;
    }
}

