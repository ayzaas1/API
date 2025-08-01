package api.qa.petstore.endpoint;

import api.qa.petstore.pojo.PJ_FindPetByID;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.ApiUtils;
import utils.ConfigReader;

public class EP_FindPetByID {


    public void validateReadingPetInfoById(String id) {
        RestAssured.baseURI = ConfigReader.readProperty("base_url");
        RestAssured.basePath = ConfigReader.readProperty("ep_find_pet_by_id");

        Response response = RestAssured.given().
                accept("application/json")
                .contentType("application/json")
                .body(ApiUtils.findPetInfoById(id))
                .when().get().then().log().body().statusCode(200).extract().response();


        PJ_FindPetByID deserializedResponse = response.as(PJ_FindPetByID.class);
        Assert.assertEquals(deserializedResponse.getId(), Integer.parseInt(id));


    }
}
