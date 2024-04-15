package pl.tropiria.backend.utilites;

import org.springframework.stereotype.Component;
import pl.tropiria.backend.species.Species;
import pl.tropiria.backend.species.SpeciesDto;

import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.UTILITY_CLASS;

public class SpeciesDtoMapper {

    private SpeciesDtoMapper() {
        throw new IllegalFormatCodePointException(UTILITY_CLASS.getCode());
    }
    public static SpeciesDto map(Species species) {
        return new SpeciesDto(species.getId(), species.getName());
    }

    public static Species map(SpeciesDto speciesDto) {
        return new Species(speciesDto.getId(), speciesDto.getName());
    }
}
