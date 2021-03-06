package ch.hearc.cuddle.models;

import javax.persistence.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@Entity
@Table(name="animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String sex;

    @Column(length = 500)
    private String description;

    @Column
    private String treatment;

    @Column
    private String image;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Transient
    public String getImagePath() {
        if (image == null || id == null) return image;

        String imgPath = "/media/img/animal/" + id + "/" + image;
        return imgPath;
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

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
