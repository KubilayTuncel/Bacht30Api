package com.techpro.day07;

import com.techpro.testBase.JsonPlaceHolderTestBase;
import com.techpro.testData.JsonPlaceHolderTestData;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest11TestData extends JsonPlaceHolderTestBase {

    @Test
    public void test() {
        JsonPlaceHolderTestData expectedObje =new JsonPlaceHolderTestData();
        HashMap<String,Object> expectedData = (HashMap<String, Object>) expectedObje.setup();

        Response response = given().accept("application/json").spec(spec01)
                .when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        response.then().assertThat().statusCode((Integer)expectedData.get("statusCode"))
                .headers("via", equalTo(expectedData.get("Via")),
                        "Server",equalTo(expectedData.get("Server"))).
                body("userId",equalTo(expectedData.get("userId")),
                        "title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")));

    }
}
