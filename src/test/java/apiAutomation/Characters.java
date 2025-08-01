package apiAutomation;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.PJ_Character;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Characters {


    @Test
    public void validateCharacterInformation() {


        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/Characters/10";


        Response response = RestAssured.given().header("Accept", "application/json").when().get().then().log().body().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });


        for (Map.Entry<String, Object> stringObjectEntry : deserializedResponse.entrySet()) {
            System.out.println(stringObjectEntry.getKey() + " = " + stringObjectEntry.getValue());


            Assert.assertEquals(deserializedResponse.get("id"), 10);
            Assert.assertEquals(deserializedResponse.get("firstName"), "Cateyln");
            Assert.assertEquals(deserializedResponse.get("lastName"), "Stark");
            Assert.assertEquals(deserializedResponse.get("fullName"), "Catelyn Stark");
            Assert.assertEquals(deserializedResponse.get("title"), "Lady of Winterfell");
            Assert.assertEquals(deserializedResponse.get("family"), "House Stark");
            Assert.assertEquals(deserializedResponse.get("image"), "catelyn-stark.jpg");
            Assert.assertEquals(deserializedResponse.get("imageUrl"), "https://thronesapi.com/assets/images/catelyn-stark.jpg");

        }
    }


    @Test
    public void validateContinents() {

        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/Continents";

        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().log().body().statusCode(200)
                .extract().response();


        List<Map<String, Object>> deserializedResponses = response.as(new TypeRef<List<Map<String, Object>>>() {
        });
        List<String> expectedContinents = Arrays.asList("Westeros", "Essos", "Sothoryos", "Ulthos");
        List<Integer> expectedIds = Arrays.asList(0, 1, 2, 3);
        //if string we can provide toString after get("id") and create list of strings

        //NOTE:Since We have List of Map. I need to use get first for List, I use second get for Map.
        for (int i = 0; i < expectedContinents.size(); i++) {
            Assert.assertEquals(deserializedResponses.get(i).get("id"), expectedIds.get(i));
            Assert.assertEquals(deserializedResponses.get(i).get("name"), expectedContinents.get(i));
        }
    }


    @Test
    public void validateCharacterInformationWithPojo() {

        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/Characters/1";

        Response response = RestAssured.given().accept("application/json").when().get().then().log().body().statusCode(200)
                .extract().response();

        //TYPE REF DESERIALIZATION
//        Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});

        //POJO Deserialization

        PJ_Character deserializedResponse = response.as(PJ_Character.class);
        Assert.assertEquals(deserializedResponse.getFirstName(), "Samwell");
        Assert.assertEquals(deserializedResponse.getLastName(), "Tarly");
        Assert.assertEquals(deserializedResponse.getId(), 1);


    }

}

