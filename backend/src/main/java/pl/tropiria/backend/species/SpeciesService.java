package pl.tropiria.backend.species;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tropiria.backend.utilites.SpeciesDtoMapper;

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

    public SpeciesDto updateSpecies(Long id, SpeciesDto speciesDto) {
           Species species = speciesRepository.findById(id)
                    .orElseThrow(() -> new IllegalFormatCodePointException(SPECIES_NOT_FOUND.getCode()));
            species.setName(speciesDto.getName());
            speciesRepository.save(species);
            return SpeciesDtoMapper.map(species);
    }

    public void deleteSpecies(Long id) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new IllegalFormatCodePointException(SPECIES_NOT_FOUND.getCode()));
        speciesRepository.delete(species);
    }
}

