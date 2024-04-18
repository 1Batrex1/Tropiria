package pl.tropiria.backend.photos;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photos")
@Data
@Builder
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "photo_path")
    private String photoPath;


}
