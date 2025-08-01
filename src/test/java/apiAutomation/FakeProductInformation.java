package apiAutomation;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.when;

public class FakeProductInformation {

    @Test
    public void validateProductInformation() {
/*To be able to do API automation you need:
     1-BaseURL + EndPoint
     2-Http Methods
     3-if needed, Request Body
     4-Header
*/
        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.basePath = "products/1";

        //Given  is all about precondition (preparation for call)
        Response response = RestAssured.given().header("Accept", "application/json")
                //When is all about action that you take with HTTP methods
                .when().get()
                //It means give me body on my console and validate status code is 200
                .then().log().body().statusCode(200).extract().response();

        //Converting JSON Object to Java Code --> DESERIALIZATION *******
        //There are 3 WAYS:
        //1- Type Ref Conversion

        //This line is all about Deserialization (converting JSON Object to JAva Code (Map)
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        System.out.println(deserializedResponse);

        Assert.assertEquals(deserializedResponse.get("id"), 1);
        Assert.assertEquals(deserializedResponse.get("title"), "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        Assert.assertEquals(deserializedResponse.get("price"), 109.95);
        Assert.assertEquals(deserializedResponse.get("description"), "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        Assert.assertEquals(deserializedResponse.get("category"), "men's clothing");
        Assert.assertEquals(deserializedResponse.get("image"), "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");


        //For every JSON Object, you need to convert it to Map

        Map<String, Object> rating = (Map<String, Object>) deserializedResponse.get("rating");
        Assert.assertEquals(rating.get("rate"), 3.9);
        Assert.assertEquals(rating.get("count"), 120);

    }
}
