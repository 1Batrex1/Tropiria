package pl.tropiria.backend.color;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tropiria.backend.utilites.ColorDtoMapper;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import static pl.tropiria.backend.config.constants.ErrorsConstant.COLOR_ALREADY_EXISTS;
import static pl.tropiria.backend.utilites.ColorDtoMapper.map;

@Service
@AllArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;

    public List<ColorDto> getColors() {

        return colorRepository.findAllByOrderByIdAsc().stream()
                .map(ColorDtoMapper::map)
                .toList();
    }

    public void saveColor(ColorDto colorDto) {
        if (isColorExists(colorDto.getName())) {
            throw new IllegalFormatCodePointException(COLOR_ALREADY_EXISTS.getCode());
        }
        colorRepository.save(map(colorDto));
    }

    public Color getColorByName(String name) {
        return colorRepository.findByName(name);
    }

    public boolean isColorExists(String name) {
        return colorRepository.findByName(name) != null;
    }
}
