package pl.tropiria.backend.animal;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a LEFT JOIN FETCH a.photoList WHERE a.id = ?1")
    Animal findById(long id);

    Animal findByName(String name);

    @Query("SELECT a FROM Animal a WHERE a.animalForSale IS NOT NULL")
    Page<Animal> findAllAnimalsForSale(Pageable pageable);

    @Query("SELECT a FROM Animal a JOIN a.animalForSale afs JOIN afs.parents p WHERE p = :parent")
    List<Animal> findAllByParent(@Param("parent") Animal parent);

    @Query("SELECT a FROM Animal a WHERE a.animalForSale IS NULL")
    List<Animal> findAllParents();
}
