package com.online.shelter.pet.servlet.util;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.model.PetWithDownplayWeight;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PetUtil {

    public static void main(String[] args) {
        List<Pet> pets = Arrays.asList(
                new Pet(LocalDate.of(2018,Month.JANUARY,29)
                ,"Dog","d1","таксф",'w',"чорный",1.5,40,16,"петя","0631234567","gui@ki"),
                new Pet(LocalDate.of(2018,Month.JANUARY,29)
                ,"Cat","c1","сиамский",'m',"белый",2,20,4,"вася","0731234567","sadr@saae"),
                new Pet(LocalDate.of(2018,Month.JANUARY,29)
                ,"Dog","d2","метис",'m',"чорный",3,60,50,"саша","0731234567","qefeq@qd"),
                new Pet(LocalDate.of(2018,Month.JANUARY,30)
                ,"Cat","c1","сеамс",'m',"белый",1,15,3.5,"катф","0671234567","arfva@dav"),
                new Pet(LocalDate.of(2018,Month.JANUARY,30)
                ,"Cat","c2","бразил",'w',"коричневый",1.8,18,4,"люда","0671234567","aeqe@eqwd"),
                new Pet(LocalDate.of(2018,Month.JANUARY,30)
                ,"Dog","d1","авчарка",'m',"белый",2,50,40.5,"вова","0731234567","aedc@ds"));

        System.out.println("Полный список");
        pets.stream()
               .forEach(pet-> System.out.println(pet));
        System.out.println("Отфильтрованый список(getFilteredWithDownplayWeight)");
        List<PetWithDownplayWeight> petWithDownplayWeights = getFilteredWithDownplayWeight(pets,Arrays.asList("","Cat",""),0.2);
        petWithDownplayWeights.forEach(System.out::println);

        System.out.println("Отфильтрованый список(getFilteredWithDownplayWeightByCycle)");
        System.out.println(getFilteredWithDownplayWeightByCycle(pets,"Dog",0.2));

    }

    //Ворма расчёта нормального веса - вес/(рост*2)
    public static List<PetWithDownplayWeight> getWithDownplayWeight(List<Pet> pets, String typePet, double normalWeight ){

          return pets.stream()
                .map(pet -> createWithDownplayWeight(pet, pet.getWeight()/(pet.getGrowth()*2) < normalWeight))
                .collect(Collectors.toList());


    }

    //Ворма расчёта нормального веса - вес/(рост*2)
    public static List<PetWithDownplayWeight> getWithDownplayWeightByCycle(List<Pet> pets, String typePet, double normalWeight ){
        final List<PetWithDownplayWeight> petWithDownplayWeights = new ArrayList<>();
        pets.forEach(pet->{
            if(pet.getTypePet().equals(typePet))
                petWithDownplayWeights.add(createWithDownplayWeight(pet
                        ,pet.getWeight()/(pet.getGrowth()*2) < normalWeight));
        });
        return petWithDownplayWeights;
    }

    //Ворма расчёта нормального веса с фильтром - вес/(рост*2)
    public static List<PetWithDownplayWeight> getFilteredWithDownplayWeight(List<Pet> pets, List<String> typePets, double normalWeight ){

          return pets.stream()
                .filter(pet -> pet.getTypePet().equals(typePets.get(0))
                || pet.getTypePet().equals(typePets.get(1)) || pet.getTypePet().equals(typePets.get(2)))
                .map(pet -> createWithDownplayWeight(pet, pet.getWeight()/(pet.getGrowth()*2) < normalWeight))
                .collect(Collectors.toList());


    }

    //Ворма расчёта нормального веса с филтром - вес/(рост*2)
    public static List<PetWithDownplayWeight> getFilteredWithDownplayWeightByCycle(List<Pet> pets, String typePet, double normalWeight ){
        final List<PetWithDownplayWeight> petWithDownplayWeights = new ArrayList<>();
        pets.forEach(pet->{
            if(pet.getTypePet().equals(typePet))
                petWithDownplayWeights.add(createWithDownplayWeight(pet
                        ,pet.getWeight()/(pet.getGrowth()*2) < normalWeight));
        });
        return petWithDownplayWeights;
    }



    public static PetWithDownplayWeight createWithDownplayWeight(Pet pet, boolean downplayWeight) {
        return new PetWithDownplayWeight(pet.getCreatedDate(),pet.getTypePet(),pet.getNamePet()
                , pet.getBreed(), pet.getSex(), pet.getColor(), pet.getAge(), pet.getGrowth()
                ,pet.getWeight(), pet.getNamePerson(), pet.getPhone(), pet.getEmail(), downplayWeight);
    }
}
