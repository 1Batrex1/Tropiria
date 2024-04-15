package pl.tropiria.backend.photos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, Long> {

    Photos findByPhotoPath(String photoPath);

}
