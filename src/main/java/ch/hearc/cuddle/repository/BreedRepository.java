package ch.hearc.cuddle.repository;

import ch.hearc.cuddle.models.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed,Long> {
}
