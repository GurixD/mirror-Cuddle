package ch.hearc.cuddle.repository;

import ch.hearc.cuddle.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal,Long> {
    List<Animal> findByName(String name);
}
