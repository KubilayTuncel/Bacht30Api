package com.techpro.day12;

import com.techpro.testBase.JsonPlaceHolderTestBase;
import com.techpro.testData.JsonPlaceHolderTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderTestBase {
    /*https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {

      "title": "API calismaliyim"

     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}

     */

    @Test
    public void test(){
        spec01.pathParams("parametre1","todos","parametre2",198);

        JsonPlaceHolderTestData obje = new JsonPlaceHolderTestData();
        JSONObject requestData = obje.requestPatch();
        JSONObject expetedData = obje.setPatch();

        Response response = given().accept("application/json").spec(spec01).auth().
                basic("admin","password123").body(requestData.toString())
                .when().patch("/{parametre1}/{parametre2");
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expetedData.getInt("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expetedData.getString("title"),jsonPath.getString("title"));
        Assert.assertEquals(expetedData.getBoolean("completed"),jsonPath.getBoolean("completed"));
        Assert.assertEquals(expetedData.getInt("id"),jsonPath.getInt("id"));

        //2.yöntem
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expetedData.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expetedData.getBoolean("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expetedData.getString("title"),actualDataMap.get("title"));
        //3. yöntem
        //Matchers class
        response.then().assertThat().
                body("completed", equalTo(expetedData.getBoolean("completed")),
                        "title",equalTo(expetedData.getString("title")),
                        "userId",equalTo(expetedData.getInt("userId")));


    }

}
