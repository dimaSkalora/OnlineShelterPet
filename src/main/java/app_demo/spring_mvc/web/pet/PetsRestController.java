package app_demo.spring_mvc.web.pet;

import app_demo.spring_mvc.model.Pet;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = PetsRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PetsRestController extends AbstractPetController {
    static final String REST_URL = "/rest/profile/petsAllUsers";

    @Override
    @GetMapping
    public List<Pet> petsAllUsers() {
        System.out.println("/rest/profile/petsAllUsers " +super.petsAllUsers());
        return super.petsAllUsers();
    }
}
