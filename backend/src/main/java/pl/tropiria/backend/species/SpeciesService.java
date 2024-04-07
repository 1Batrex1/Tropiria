package pl.tropiria.backend.species;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tropiria.backend.utilites.SpeciesDtoMapper;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import static pl.tropiria.backend.config.constants.ErrorsConstant.SPECIES_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    public List<SpeciesDto> getSpecies() {
        return speciesRepository
                .findAllByOrderByIdAsc()
                .stream()
                .map(SpeciesDtoMapper::map)
                .toList();
    }

    public void saveSpecies(SpeciesDto speciesDto) {
        if (isSpeciesExists(speciesDto.getName())) {
            throw new IllegalFormatCodePointException(SPECIES_ALREADY_EXISTS.getCode());
        }
        speciesRepository.save(SpeciesDtoMapper.map(speciesDto));
    }

    public Species getSpeciesByName(String name) {
        return speciesRepository.findByName(name);
    }

    public boolean isSpeciesExists(String name) {
        return speciesRepository.findByName(name) != null;
    }
}

