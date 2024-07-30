package pl.tropiria.backend.species;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import static pl.tropiria.backend.config.constants.ErrorsConstant.*;

@Service
@AllArgsConstructor
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    public List<SpeciesDto> getSpecies() {
        return speciesRepository
                .findAllByOrderByIdAsc()
                .stream()
                .map(species -> SpeciesDto.builder()
                        .id(species.getId())
                        .name(species.getName())
                        .build())
                .toList();
    }

    public void saveSpecies(SpeciesDto speciesDto) {
        if (isSpeciesExists(speciesDto.getName())) {
            throw new IllegalFormatCodePointException(SPECIES_ALREADY_EXISTS.CODE);
        }
        speciesRepository.save(Species.builder()
                .name(speciesDto.getName())
                .build());
    }

    public Species getSpeciesByName(String name) {
        return speciesRepository.findByName(name);
    }

    public boolean isSpeciesExists(String name) {
        return speciesRepository.findByName(name) != null;
    }



    public SpeciesDto updateSpecies(Long id, SpeciesDto speciesDto) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new IllegalFormatCodePointException(SPECIES_NOT_FOUND.CODE));
        species.setName(speciesDto.getName());
        speciesRepository.save(species);
        return SpeciesDto.builder()
                .id(species.getId())
                .name(species.getName())
                .build();
    }

    public void deleteSpecies(Long id) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new IllegalFormatCodePointException(SPECIES_NOT_FOUND.CODE));
        speciesRepository.delete(species);
    }
}

