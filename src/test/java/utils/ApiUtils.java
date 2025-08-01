package utils;


//instead of body == Payload (is A KEYWORD!!)

public class ApiUtils {
    public static String createPetRequestPayload(String id, String name, String status) {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"puppy\"\n" +
                "  },\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"www.dogpicture.com/pamuk.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Trained\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
    }


    public static String findPetInfoById(String id) {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "}";
    }

}
