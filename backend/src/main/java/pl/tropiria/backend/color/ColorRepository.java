package pl.tropiria.backend.color;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    List<Color> findAllByOrderByIdAsc();

    Color findByName(String name);
}
