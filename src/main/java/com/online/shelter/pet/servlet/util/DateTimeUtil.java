package com.online.shelter.pet.servlet.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM dd");

    public static String toString(LocalDate ld){
        return ld == null ? "" : ld.format(DATE_TIME_FORMATTER);
    }
}
