package com.online.shelter.pet.servlet;

import static com.online.shelter.pet.servlet.util.PetUtil.DEFAULT_NOLMAL_WEIGHT;

public class AuthorizedUser {
    private static int id = 1;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static double getNormalWeight() {
        return DEFAULT_NOLMAL_WEIGHT;
    }
}
