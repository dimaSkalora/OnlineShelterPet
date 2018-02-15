package com.online.shelter.pet.spring_mvc.util;

import java.time.LocalTime;

public class TimeUtil {

    private TimeUtil(){

    }
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
