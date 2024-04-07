package pl.tropiria.backend.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal findById(long id);

    Animal findByName(String name);
}
