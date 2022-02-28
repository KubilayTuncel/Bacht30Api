package com.techpro.day14;

import com.techpro.testBase.JsonPlaceHolderTestBase;
import com.techpro.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper01 extends JsonPlaceHolderTestBase {
    /* https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
Dönen response ‘un status kodunun 200 ve body kısmının
{
   "userId": 10,
   "id": 198,
   "title": "quis eius est sint explicabo",
   "completed": true
}

     */

    @Test
    public void test(){
        spec01.pathParams("parametre1","todos","parametre2",198);

        String jsonData="{\n" +
                "   \"userId\": 10,\n" +
                "   \"id\": 198,\n" +
                "   \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                "}";

        Map<String,Object> expectedData = JsonUtil.convertJsonToJava(jsonData, Map.class);

        Response response = given().contentType(ContentType.JSON).spec(spec01)
                .when().get("/{parametre1}/{parametre2}");

        Map<String,Object> actualData = JsonUtil.convertJsonToJava(response.asString(),Map.class);

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));

    }
}
