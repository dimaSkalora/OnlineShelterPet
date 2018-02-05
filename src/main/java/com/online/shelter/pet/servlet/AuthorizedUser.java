package com.online.shelter.pet.servlet;

import static com.online.shelter.pet.servlet.util.PetUtil.DEFAULT_NOLMAL_WEIGHT;

public class AuthorizedUser {
    public static int id() {
        return 1;
    }

    public static double getCaloriesPerDay() {
        return DEFAULT_NOLMAL_WEIGHT;
    }
}
