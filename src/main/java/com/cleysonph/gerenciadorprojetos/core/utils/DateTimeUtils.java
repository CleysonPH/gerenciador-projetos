package com.cleysonph.gerenciadorprojetos.core.utils;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class DateTimeUtils {

    private DateTimeUtils() {}

    public static ZonedDateTime now() {
        return ZonedDateTime.now();
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

}
