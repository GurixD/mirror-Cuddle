package ch.hearc.cuddle.repository;

import ch.hearc.cuddle.models.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed,Long> {
    List<Breed> findByName(String name);
}
