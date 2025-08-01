package apiAutomation;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class Booking {


    @Test
    public void validateBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/{id}";

        //id is path parameter in {},

        Response response = RestAssured.given().accept("application/json")
                .pathParams("id", 44)
                .when().get().
                then().log().body().statusCode(200).extract().response();

//Deserialize (convert JSON to JAVA code)

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(deserializedResponse.get("firstname"));
        Assert.assertEquals(deserializedResponse.get("firstname"), "Josh");

        Map<String, Object> bookingDates = (Map<String, Object>) deserializedResponse.get("bookingdates");
        Assert.assertEquals(bookingDates.get("checkin"), "2018-01-01");
    }

    //Request body example (POST)
    @Test
    public void validateAuthToken() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "auth";

        Response response = RestAssured.given().header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when().post()
                .then().log().body().statusCode(200).extract().response();

        //Deserialize

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        //Validation

        //AssertNotnull checks the value of token is null(empty or not)
        Assert.assertNotNull(deserializedResponse.get("token"));
        Assert.assertEquals(deserializedResponse.get("token").toString().length(), 15);

    }


    @Test
    public void validateCreateBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking";

        Response response = RestAssured.given().contentType("application/json").accept("application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Aiza\",\n" +
                        "    \"lastname\" : \"Asan\",\n" +
                        "    \"totalprice\" : 100,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2025-06-09\",\n" +
                        "        \"checkout\" : \"2025-08-02\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"IT WORLD\"\n" +
                        "}")
                .when().post()
                .then().log().body().statusCode(200).extract().response();


        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(deserializedResponse.get("bookingid"));
        Assert.assertNotNull(deserializedResponse.get("bookingid"));

        Map<String, Object> booking = (Map<String, Object>) deserializedResponse.get("booking");
        System.out.println(booking.get("firstname"));
        Assert.assertEquals(booking.get("firstname"), "Aiza");

        Map<String, Object> bookingdates = (Map<String, Object>) booking.get("bookingdates");
        System.out.println(bookingdates.get("checkin"));
        Assert.assertEquals(bookingdates.get("checkin"), "2025-06-09");
    }


    @Test
    public void validateUpdateBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/{id}";


        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParams("id", 4288)
                .body("{\n" +
                        "    \"firstname\" : \"Name\",\n" +
                        "    \"lastname\" : \"Surname\",\n" +
                        "    \"totalprice\" : 99,\n" +
                        "    \"depositpaid\" : false,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2025-06-09\",\n" +
                        "        \"checkout\" : \"2025-08-02\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"IT WORLD wanted\"\n" +
                        "}")
                .when().put()
                .then().log().body().statusCode(200).extract().response();

        Map<String, Object> booking = response.as(new TypeRef<Map<String, Object>>() {
        });
        Assert.assertEquals(booking.get("firstname"), "Name");
        Assert.assertEquals(booking.get("lastname"), "Surname");
        Assert.assertEquals(booking.get("totalprice"), 99);
        Assert.assertEquals(booking.get("depositpaid"), false);
        Assert.assertEquals(booking.get("additionalneeds"), "IT WORLD wanted");

        Map<String, Object> bookingdates = (Map<String, Object>) booking.get("bookingdates");

        Assert.assertEquals(bookingdates.get("checkin"), "2025-06-09");
        Assert.assertEquals(bookingdates.get("checkout"), "2025-08-02");


    }


    @Test
    public void validateBookingWithJsonPath() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/{id}";

        //id is path parameter in {},

        Response response = RestAssured.given().accept("application/json")
                .pathParams("id", 30)
                .when().get().
                then().log().body().statusCode(200).extract().response();

//Deserialization

        JsonPath deserializedResponse = response.jsonPath();
        Assert.assertEquals(deserializedResponse.get("firstname"), "John");
        Assert.assertEquals(deserializedResponse.get("bookingdates.checkin"), "2018-01-01");
    }
}




