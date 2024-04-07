package pl.tropiria.backend.species;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

    List<Species> findAllByOrderByIdAsc();

    Species findByName(String name);
}
