package pl.tropiria.backend.morph;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import static pl.tropiria.backend.config.constants.ErrorsConstant.MORPH_ALREADY_EXISTS;
import static pl.tropiria.backend.config.constants.ErrorsConstant.MORPH_NOT_FOUND;

@Service
@AllArgsConstructor
public class MorphService {

    private final MorphRepository morphRepository;

    public List<MorphDto> getMorphs() {

        return morphRepository.findAllByOrderByIdAsc().stream()
                .map(morph -> MorphDto.builder()
                        .id(morph.getId())
                        .name(morph.getName())
                        .build())
                .toList();
    }

    public void saveMorph(MorphDto morphDto) {
        if (isMorphExists(morphDto.getName())) {
            throw new IllegalFormatCodePointException(MORPH_ALREADY_EXISTS.getCode());
        }
        morphRepository.save(Morph.builder()
                .name(morphDto.getName())
                .build());
    }

    public MorphDto updateMorph(Long id, MorphDto morphDto) {
        Morph morph = morphRepository.findById(id)
                .orElseThrow(() -> new IllegalFormatCodePointException(MORPH_NOT_FOUND.getCode()));
        morph.setName(morphDto.getName());
        morphRepository.save(morph);
        return MorphDto.builder()
                .id(morph.getId())
                .name(morph.getName())
                .build();
    }

    public void deleteMorph(Long id) {
        Morph morph = morphRepository.findById(id)
                .orElseThrow(() -> new IllegalFormatCodePointException(MORPH_NOT_FOUND.getCode()));
        morphRepository.delete(morph);
    }

    public Morph getMorphByName(String name) {
        return morphRepository.findByName(name);
    }

    public boolean isMorphExists(String name) {
        return morphRepository.findByName(name) != null;
    }
}
