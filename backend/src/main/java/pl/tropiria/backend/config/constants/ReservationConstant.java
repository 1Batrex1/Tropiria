package pl.tropiria.backend.config.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationConstant {
    FOR_SALE("NA SPRZEDAŻ"),
    RESERVED("ZAREZERVOWANO");

    private final String status;


}
