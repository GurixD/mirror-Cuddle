package ch.hearc.cuddle.repository;

import ch.hearc.cuddle.models.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeciesRepository extends JpaRepository<Species,Long> {
    List<Species> findByName(String name);
}
