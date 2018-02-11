package com.online.shelter.pet.servlet;

import com.online.shelter.pet.servlet.model.Pet;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.online.shelter.pet.servlet.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class PetTestData {
    public static final int PET_ID = START_SEQ + 2;
    public static final int ADMIN_PET_ID = START_SEQ + 8;


    public static final Pet PET1 = new Pet(PET_ID, LocalDateTime.of(2018, Month.JANUARY,29,10,0)
                    ,"Dog","d1","таксф","w","чорный",1.5,40,16,"петя","0631234567","gui@ki");
    public static final Pet PET2 = new Pet(PET_ID+1, LocalDateTime.of(2018,Month.JANUARY,29,20,0)
                    ,"Cat","c1","сиамский","m","белый",2,20,4,"вася","0731234567","sadr@saae");
    public static final Pet PET3 = new Pet(PET_ID+2, LocalDateTime.of(2018,Month.JANUARY,29,22,0)
                    ,"Dog","d2","метис","m","чорный",3,60,50,"саша","0731234567","qefeq@qd");
    public static final Pet PET4 = new Pet(PET_ID+3, LocalDateTime.of(2018,Month.JANUARY,30,10,0)
                    ,"Cat","c1","сеамс","m","белый",1,15,3.5,"катф","0671234567","arfva@dav");
    public static final Pet PET5 = new Pet(PET_ID+4, LocalDateTime.of(2018,Month.JANUARY,30,15,0)
                    ,"Cat","c2","бразил","w","коричневый",1.8,18,4,"люда","0671234567","aeqe@eqwd");
    public static final Pet PET6 = new Pet(PET_ID+5, LocalDateTime.of(2018,Month.JANUARY,30,20,0)
                    ,"Dog","d1","авчарка","m","белый",2,50,40.5,"вова","0731234567","aedc@ds");
    public static final Pet ADMIN_PET1 = new Pet(ADMIN_PET_ID,LocalDateTime.of(2018,Month.FEBRUARY,5,10,0)
                ,"Cat","caty","бразил","w","коричневый",
                        1.8,18,4,"люда","0671234567","aeqe@eqwd");
    public static final Pet ADMIN_PET2 = new Pet(ADMIN_PET_ID+1,LocalDateTime.of(2018,Month.FEBRUARY,5,18,0)
                ,"Dog","dogy","бразил","m","коричневый",
                        1.8,15,4,"anna","0489449494","aeqsfsdre@eqwd");

    public static final List<Pet> PETS = Arrays.asList(PET6, PET5,PET4,PET4,PET3,PET2,PET1);

    public static Pet getCreated() {
        return  new Pet(null, LocalDateTime.of(2018, Month.JANUARY,29,11,0)
                ,"Dog","getCreated","таксф","w","чорный",1.5,40,16,"петя","0631234567","gui@ki");

    }

    public static Pet getUpdated() {
        return  new Pet(PET_ID, PET1.getCreatedDate()
                ,"Dog","getUpdated","таксф","w","чорный",1.5,40,16,"петя","0631234567","gui@ki");

    }

    public static void assertMatch(Pet actual, Pet expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Pet> actual, Pet... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Pet> actual, Iterable<Pet> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }
}
