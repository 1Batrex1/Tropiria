package pl.tropiria.backend.species;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpeciesDto {

    private long id;
    private String name;

    public SpeciesDto(String name) {
        this.name = name;
    }

}
