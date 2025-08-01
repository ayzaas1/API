package apiAutomation;


import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CatFacts {

        /*
1-Call the endpoint
2-Count How many of them has more than 50 length --> 299
3-Count How many of them has less than 200 length -->293
4-Count How many of them more than 50 and less than 200 at the same time -->260
5-Count How many of them not contains cat in facts. -->25
 */

    @Test
    public void validateCatFacts() {
        RestAssured.baseURI = "https://catfact.ninja";
        RestAssured.basePath = "facts";

        Response response = RestAssured.given().accept("application/json")
                .queryParam("limit", 332)
                .when().get().then()
                .log().body().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {});

        List<Map<String, Object>> allData = (List<Map<String, Object>>) deserializedResponse.get("data");

        int moreThan50 = 0;
        int lessThan200 = 0;
        int more50less200 = 0;
        int notContainCat = 0;

        for (Map<String, Object> data : allData) {
            if (Integer.parseInt(data.get("length").toString()) > 50) {
                moreThan50++;
            }

            if (Integer.parseInt(data.get("length").toString()) < 200) {
                lessThan200++;
            }

            if (Integer.parseInt(data.get("length").toString()) < 200 && Integer.parseInt(data.get("length").toString()) > 50) {
                more50less200++;
            }

            if (!data.get("fact").toString().toLowerCase().contains("cat")) {
                notContainCat++;
            }
        }

        Assert.assertEquals(moreThan50, 299);
        Assert.assertEquals(lessThan200, 293);
        Assert.assertEquals(more50less200, 260);
        Assert.assertEquals(notContainCat, 25);


    }


}




