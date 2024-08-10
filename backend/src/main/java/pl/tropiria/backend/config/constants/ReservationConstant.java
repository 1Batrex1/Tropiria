package pl.tropiria.backend.config.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.IllegalFormatCodePointException;

public class ReservationConstant {

    private ReservationConstant () {
        throw new IllegalFormatCodePointException(ErrorsConstant.UTILITY_CLASS.CODE);
    }

    public static final String FOR_SALE = "NA SPRZEDAŻ";
    public static final String RESERVED = "ZAREZERWOWANO";
    public static final String SOLD = "SPRZEDANO";




}
