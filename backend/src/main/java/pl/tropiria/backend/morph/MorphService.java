package pl.tropiria.backend.morph;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tropiria.backend.utilites.MorphDtoMapper;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import static pl.tropiria.backend.config.constants.ErrorsConstant.MORPH_ALREADY_EXISTS;
import static pl.tropiria.backend.utilites.MorphDtoMapper.map;

@Service
@AllArgsConstructor
public class MorphService {

    private final MorphRepository morphRepository;

    public List<MorphDto> getColors() {

        return morphRepository.findAllByOrderByIdAsc().stream()
                .map(MorphDtoMapper::map)
                .toList();
    }

    public void saveColor(MorphDto morphDto) {
        if (isMorphExists(morphDto.getName())) {
            throw new IllegalFormatCodePointException(MORPH_ALREADY_EXISTS.getCode());
        }
        morphRepository.save(map(morphDto));
    }

    public Morph getMorphByName(String name) {
        return morphRepository.findByName(name);
    }

    public boolean isMorphExists(String name) {
        return morphRepository.findByName(name) != null;
    }
}
