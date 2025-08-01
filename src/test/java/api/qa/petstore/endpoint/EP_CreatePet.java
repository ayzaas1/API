package api.qa.petstore.endpoint;

import api.qa.petstore.pojo.PJ_CreatePet;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.ApiUtils;
import utils.ConfigReader;

public class EP_CreatePet {

    public void validateCreationOfPet(String id, String name, String status) {
        RestAssured.baseURI = ConfigReader.readProperty("base_url");
        RestAssured.basePath = ConfigReader.readProperty("ep_create_pet");

        Response response = RestAssured.given().
                accept("application/json")
                .contentType("application/json")
                .body(ApiUtils.createPetRequestPayload(id, name, status))
                .when().post().then().log().body().statusCode(200).extract().response();

        PJ_CreatePet deserializedResponse = response.as(PJ_CreatePet.class);
        Assert.assertEquals(deserializedResponse.getId(), Integer.parseInt(id));
        Assert.assertEquals(deserializedResponse.getName(), name);
        Assert.assertEquals(deserializedResponse.getStatus(), status);

        //inside of Category

        Assert.assertEquals(deserializedResponse.getCategory().getId(), 0);
        Assert.assertEquals(deserializedResponse.getCategory().getName(), "puppy");

    }
}
