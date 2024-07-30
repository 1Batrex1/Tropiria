package pl.tropiria.backend.animal;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal findById(long id);

    Animal findByName(String name);

    @Query("SELECT a FROM Animal a WHERE a.animalForSale IS NOT NULL")
    Page<Animal> findAllAnimalsForSale(Pageable pageable);
}
