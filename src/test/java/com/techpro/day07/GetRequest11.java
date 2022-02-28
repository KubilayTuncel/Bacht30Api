package com.techpro.day07;

import com.techpro.testBase.HerokuAppTEstBase;
import com.techpro.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest11  extends JsonPlaceHolderTestBase {
    //https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
    // Dönen response un
    // Status kodunun 200, dönen body de,
    //       "completed": değerinin false
    //       "title”: değerinin “quis ut nam facilis et officia qui”
    //       "userId" sinin 1 ve header değerlerinden
    // "Via" değerinin “1.1 vegur” ve
    //       "Server" değerinin “cloudflare” olduğunu test edin…

    @Test
    public void test1(){
        spec01.pathParams("parametre1","todos","parametre",2);
        HashMap<String,Object> expectedData = new HashMap<String, Object>();

        expectedData.put("statusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        System.out.println(expectedData);

        Response response = given().accept("application/json").spec(spec01)
                .when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        response.then().assertThat().statusCode((Integer)expectedData.get("statusCode"))
                .headers("via", equalTo(expectedData.get("Via")),
                        "Server",equalTo(expectedData.get("Server"))).
                body("userId",equalTo(expectedData.get("userId")),
                "title",equalTo(expectedData.get("title")),
                "completed",equalTo(expectedData.get("completed")));
        //2. Yöntem

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"),response.statusCode());
        Assert.assertEquals(expectedData.get("via"),response.getHeader("Via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("copmleted"),jsonPath.getBoolean("copmleted"));

        //3. Yöntem
        HashMap<String, Object> actualData =response.as(HashMap.class);
        System.out.println(actualData);
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("copmleted"),actualData.get("completed"));

    }

}
