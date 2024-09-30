package pl.tropiria.backend.photo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photo")
@Data
@Builder
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "photo_name")
    @NotNull
    private String photoName;


}
