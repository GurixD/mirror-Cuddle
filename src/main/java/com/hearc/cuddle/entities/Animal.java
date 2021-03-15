package com.hearc.cuddle.entities;

import javax.persistence.*;

@Entity
@Table(name="animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @OneToOne
//    @JoinColumn()
//    private Breed breed;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
