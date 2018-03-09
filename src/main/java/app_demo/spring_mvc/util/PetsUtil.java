package app_demo.spring_mvc.util;

import app_demo.spring_mvc.model.Pet;
import app_demo.spring_mvc.to.PetWithDownplayWeight;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


public class PetsUtil {
    private PetsUtil() {
    }

    //отобрасить список PetWithDownplayWeight
    public static List<PetWithDownplayWeight> getWithDownplayWeight(Collection<Pet> pets, double normalWeight){
        return getFilteredWithDownplayWeight(pets,LocalTime.MIN, LocalTime.MAX, Arrays.asList("Cat","Dog","Others"),normalWeight);
    }

    //отобрасить список pet
    public static List<Pet> getPetAll(Collection<Pet> pets){
        return getFilteredPetAll(pets,LocalTime.MIN, LocalTime.MAX, Arrays.asList("Cat","Dog","Others"));
    }

    //Ворма расчёта нормального веса с фильтром - вес/(рост*2)
    public static List<PetWithDownplayWeight> getFilteredWithDownplayWeight(Collection<Pet> pets, LocalTime startTime, LocalTime endTime, List<String> typePets, double normalWeight ){

        return pets.stream()
                .filter(pet -> DateTimeUtil.isBetween(pet.getTime(), startTime, endTime))
                .filter(pet -> pet.getTypePet().equals(typePets.get(0))
                        || pet.getTypePet().equals(typePets.get(1)) || pet.getTypePet().equals(typePets.get(2)))
                .map(pet -> createWithDownplayWeight(pet, pet.getWeight()/(pet.getGrowth()*2) < normalWeight))
                .collect(Collectors.toList());

    }

    //Список всех pets
    public static List<Pet> getFilteredPetAll(Collection<Pet> pets, LocalTime startTime, LocalTime endTime, List<String> typePets){

        return pets.stream()
                .filter(pet -> DateTimeUtil.isBetween(pet.getTime(), startTime, endTime))
                .filter(pet -> pet.getTypePet().equals(typePets.get(0))
                        || pet.getTypePet().equals(typePets.get(1)) || pet.getTypePet().equals(typePets.get(2)))
                .map(pet -> createPet(pet))
                .collect(Collectors.toList());

    }

    public static Pet createPet(Pet pet) {
        return new Pet(pet.getId(),pet.getCreatedDate(),pet.getTypePet(),pet.getNamePet()
                , pet.getBreed(), pet.getSex(), pet.getColor(), pet.getAge(), pet.getGrowth()
                ,pet.getWeight(), pet.getNamePerson(), pet.getPhone(), pet.getEmail());
    }

    public static PetWithDownplayWeight createWithDownplayWeight(Pet pet, boolean downplayWeight) {
        return new PetWithDownplayWeight(pet.getId(),pet.getCreatedDate(),pet.getTypePet(),pet.getNamePet()
                , pet.getBreed(), pet.getSex(), pet.getColor(), pet.getAge(), pet.getGrowth()
                ,pet.getWeight(), pet.getNamePerson(), pet.getPhone(), pet.getEmail(), downplayWeight);
    }

  /*  //отобрасить список PetWithDownplayWeight
    public static List<PetWithDownplayWeight> getWithDownplayWeight(Collection<Pet> pets, double normalWeight){
        return getFilteredWithDownplayWeight(pets,Arrays.asList("Cat","Dog","Others"),normalWeight);
    }

    //Ворма расчёта нормального веса с фильтром - вес/(рост*2)
    public static List<PetWithDownplayWeight> getFilteredWithDownplayWeight(Collection<Pet> pets, List<String> typePets, double normalWeight ){

        return pets.stream()
                .filter(pet -> pet.getTypePet().equals(typePets.get(0))
                        || pet.getTypePet().equals(typePets.get(1)) || pet.getTypePet().equals(typePets.get(2)))
                .map(pet -> createWithDownplayWeight(pet, pet.getWeight()/(pet.getGrowth()*2) < normalWeight))
                .collect(Collectors.toList());

    }*/

/*    //отобрасить список PetWithDownplayWeight
    public static List<PetWithDownplayWeight> getWithDownplayWeight(Collection<Pet> pets, double normalWeight){
        return getFilteredWithDownplayWeight(pets,"Cat",normalWeight);
    }

    //Ворма расчёта нормального веса с фильтром - вес/(рост*2)
    public static List<PetWithDownplayWeight> getFilteredWithDownplayWeight(Collection<Pet> pets, String typePet, double normalWeight ){

        return pets.stream()
                //.filter(pet -> pet.getTypePet().equals(typePet))
                .map(pet -> createWithDownplayWeight(pet, pet.getWeight()/(pet.getGrowth()*2) < normalWeight))
                .collect(Collectors.toList());

    }*/




/*
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



    //Ворма расчёта нормального веса с филтром - вес/(рост*2)
    public static List<PetWithDownplayWeight> getFilteredWithDownplayWeightByCycle(List<Pet> pets, String typePet, double normalWeight ){
        final List<PetWithDownplayWeight> petWithDownplayWeights = new ArrayList<>();
        pets.forEach(pet->{
            if(pet.getTypePet().equals(typePet))
                petWithDownplayWeights.add(createWithDownplayWeight(pet
                        ,pet.getWeight()/(pet.getGrowth()*2) < normalWeight));
        });
        return petWithDownplayWeights;
    }*/
}
