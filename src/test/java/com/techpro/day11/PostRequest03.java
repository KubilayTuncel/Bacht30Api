package com.techpro.day11;

import com.techpro.testBase.JsonPlaceHolderTestBase;
import com.techpro.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Assert;
import java.util.HashMap;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;


public class PostRequest03 extends JsonPlaceHolderTestBase {

    /*https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
   {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false,
     "id": …
    }

     */
    @Test
    public void test(){

        spec01.pathParam("parametre","todos");
        JsonPlaceHolderTestData obje=new JsonPlaceHolderTestData();
        JSONObject expectedData = obje.setupExpectedDAta();

        Response response = given().accept(ContentType.JSON).spec(spec01).auth()
                .basic("admin","password123").body(expectedData.toString()).when().post("/{parametre}");
        response.then().assertThat()
                .statusCode(expectedData.getInt("statusCode"))
                .body("completed", equalTo(expectedData.getBoolean("completed")),
                        "title",equalTo(expectedData.getString("title")),
                        "userId",equalTo(expectedData.getInt("userId")));
        // de serialization
        HashMap<String, Object> actualData=response.as(HashMap.class);

        System.out.println(actualData);
        Assert.assertEquals(expectedData.getBoolean("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.getString("title"),actualData.get("title"));

    }


}
