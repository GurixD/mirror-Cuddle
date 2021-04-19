package ch.hearc.cuddle.repository;

import ch.hearc.cuddle.models.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species,Long> {
}
