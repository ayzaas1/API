package api.qa.petstore.test;

import api.qa.petstore.endpoint.EP_CreatePet;
import org.testng.annotations.Test;

public class CreatePetTests {

    @Test
    public void validatePetCreation() {
        EP_CreatePet epCreatePet = new EP_CreatePet();
        epCreatePet.validateCreationOfPet("87","Cotton","Available");
    }
}
