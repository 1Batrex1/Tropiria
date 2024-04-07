package pl.tropiria.backend.morph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MorphRepository extends JpaRepository<Morph, Long> {

    List<Morph> findAllByOrderByIdAsc();

    Morph findByName(String name);
}
