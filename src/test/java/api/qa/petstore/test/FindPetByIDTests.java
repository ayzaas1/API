package api.qa.petstore.test;

import api.qa.petstore.endpoint.EP_FindPetByID;
import org.testng.annotations.Test;

public class FindPetByIDTests {

    @Test
    public void validateReadingPetInfoById(){
        EP_FindPetByID epFindPetByID = new EP_FindPetByID();
        epFindPetByID.validateReadingPetInfoById("87");
    }



}
