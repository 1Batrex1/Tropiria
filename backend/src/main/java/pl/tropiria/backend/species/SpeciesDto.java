package pl.tropiria.backend.species;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SpeciesDto {

    private long id;
    private String name;

    public SpeciesDto(String name) {
        this.name = name;
    }

}
