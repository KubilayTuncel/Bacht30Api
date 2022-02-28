package com.techpro.day12;

import com.techpro.testBase.DummyTestBase;
import com.techpro.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class DeleteRequest01 extends DummyTestBase {

    @Test
    public void test(){

        /*http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
}

         */
        spec03.pathParams("parametre1","delete","parametre2",2);
        DummyTestData obje = new DummyTestData();
        JSONObject expectedData = obje.setDelete();

        Response response = given().accept("application/json").spec(spec03).auth()
                .basic("admin","password123")
                .when().delete("/{parametre1}/{parametre2}");

        JsonPath jsonPath =response.jsonPath();
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.getString("status"),jsonPath.getString("status"));
        Assert.assertEquals(expectedData.getString("data"),jsonPath.getString("data"));
        Assert.assertEquals(expectedData.getString("message"),jsonPath.getString("message"));

        //2.yöntem
        response.then().assertThat()
                .statusCode(expectedData.getInt("statusCode"))
                .body("status", equalTo(expectedData.getString("status")),
                        "data",equalTo(expectedData.getString("data")),
                        "message",equalTo(expectedData.getString("message")));

    }
}
