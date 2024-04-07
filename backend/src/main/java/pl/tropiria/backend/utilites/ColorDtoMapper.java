package pl.tropiria.backend.utilites;

import pl.tropiria.backend.color.Color;
import pl.tropiria.backend.color.ColorDto;

import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.UTILITY_CLASS;

public class ColorDtoMapper {


    private ColorDtoMapper() {
        throw new IllegalFormatCodePointException(UTILITY_CLASS.getCode());
    }

    public static Color map(ColorDto colorDto) {
        return new Color(colorDto.getId(), colorDto.getName());
    }

    public static ColorDto map(Color color) {
        return new ColorDto(color.getId(), color.getName());
    }
}
