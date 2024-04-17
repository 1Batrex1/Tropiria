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

}
