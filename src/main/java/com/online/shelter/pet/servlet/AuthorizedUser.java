package com.online.shelter.pet.servlet;

import com.online.shelter.pet.servlet.model.AbstractBaseEntity;

import static com.online.shelter.pet.servlet.util.PetUtil.DEFAULT_NOLMAL_WEIGHT;

public class AuthorizedUser {
    private static int id = AbstractBaseEntity.START_SEQ;

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
