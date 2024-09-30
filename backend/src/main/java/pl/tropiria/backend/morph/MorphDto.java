package pl.tropiria.backend.morph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class MorphDto {
    private long id;
    private String name;

    public MorphDto(String name) {
        this.name = name;
    }

    public static MorphDto toDto(Morph morph) {
        return MorphDto.builder()
                .id(morph.getId())
                .name(morph.getName())
                .build();
    }

    public static Morph toEntity(MorphDto morphDto) {
        return Morph.builder()
                .id(morphDto.getId())
                .name(morphDto.getName())
                .build();
    }
}
