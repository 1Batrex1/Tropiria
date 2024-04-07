package pl.tropiria.backend.config.constants;

import lombok.Getter;

@Getter
public enum ReservationConstant {
    FOR_SALE("NA SPRZEDAŻ"),
    RESERVED("ZAREZERVOWANO");

    private final String status;

    ReservationConstant(String status) {
        this.status = status;
    }
}
