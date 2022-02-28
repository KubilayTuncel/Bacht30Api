package com.techpro.day06;

import com.techpro.testBase.HerokuAppTEstBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;

public class GetRequest07 extends HerokuAppTEstBase {
    //https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
    //        HTTP Status Code’unun 200
    //        ve response content type’inin “application/JSON” oldugunu
    //            ve response body’sinin asagidaki gibi oldugunu test edin
    //                {"firstname": Sally,
    //                "lastname": "Smith",
    //                "totalprice": 789,
    //                "depositpaid": false,
    //                "bookingdates": {     "checkin": "2017-12-11",
    //                                                    "checkout":"2020-02-20" }
    //            }

    @Test
    public void test(){
        spec02.pathParams("parametre1","booking","parametre2",5);
        Response response = given().accept("application/json").spec(spec02)
                .when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON).body("firstname",equalTo("Sally"),
                        "lastname",equalTo("Smith"),"totalprice",equalTo(789),"depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2017-12-11"),
                        "bookingdates.checkout",equalTo("2020-02-20"));

        //2. yöntem
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("Sally",jsonPath.getString("firstname"));
        Assert.assertEquals("Smith",jsonPath.getString("lastname"));
        Assert.assertEquals(789,jsonPath.getInt("totalprice"));
        Assert.assertEquals(true,jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals("2020-10-31",jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("2021-06-26",jsonPath.getString("bookingdates.checkout"));
    }
}
