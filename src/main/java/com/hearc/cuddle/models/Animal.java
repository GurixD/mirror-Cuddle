package com.hearc.cuddle.models;

import javax.persistence.*;

@Entity
@Table(name="animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "breed_id", referencedColumnName = "id")
    private Breed breed;

    @OneToOne
    @JoinColumn(name = "species_id", referencedColumnName = "id")
    private Species species;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Species getSpecies() {
        return species;
    }
}
