package com.online.shelter.pet.spring_mvc;

import com.online.shelter.pet.spring_mvc.model.AbstractBaseEntity;
import com.online.shelter.pet.spring_mvc.util.PetsUtil;

public class AuthorizedUser {
    private static int id = AbstractBaseEntity.START_SEQ;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static double getNormalWeight() {
        return PetsUtil.DEFAULT_NOLMAL_WEIGHT;
    }
}
