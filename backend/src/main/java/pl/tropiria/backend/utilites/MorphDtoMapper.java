package pl.tropiria.backend.utilites;

import pl.tropiria.backend.morph.Morph;
import pl.tropiria.backend.morph.MorphDto;

import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.UTILITY_CLASS;

public class MorphDtoMapper {


    private MorphDtoMapper() {
        throw new IllegalFormatCodePointException(UTILITY_CLASS.getCode());
    }

    public static Morph map(MorphDto morphDto) {
        return new Morph(morphDto.getId(), morphDto.getName());
    }

    public static MorphDto map(Morph morph) {
        return new MorphDto(morph.getId(), morph.getName());
    }
}
