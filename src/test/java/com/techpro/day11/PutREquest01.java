package com.techpro.day11;

import com.techpro.testBase.JsonPlaceHolderTestBase;
import com.techpro.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutREquest01 extends JsonPlaceHolderTestBase {
    /*https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
   {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false,
 "id": 198
}

     */

    @Test
    public void test(){
        spec01.pathParams("parametre1","todos","parametre2",198);
        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest=testObje.setupPut();

        Response response=given().contentType(ContentType.JSON).spec(spec01).auth().basic("admin","password123")
                .body(expectedRequest.toString()).when().put("/{parametre1}/{parametre2}");

        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),jsonPath.getBoolean("completed"));

    }
}
