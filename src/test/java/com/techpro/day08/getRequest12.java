package com.techpro.day08;

import com.techpro.testBase.HerokuAppTEstBase;
import com.techpro.testData.HerokuappTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class getRequest12 extends HerokuAppTEstBase {
    //https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
    // dönen response body nin
    //  {
    //   "firstname": "Eric",
    //   "lastname": "Smith",
    //   "totalprice": 555,
    //   "depositpaid": false,
    //   "bookingdates": {
    //       "checkin": "2016-09-09",
    //       "checkout": "2017-09-21"
    //    }
    //} gibi olduğunu test edin

    @Test
    public void test(){
        HerokuappTestData expectedObje = new HerokuappTestData();
        HashMap<String,Object> expectedData = expectedObje.setupTestData();

        spec02.pathParams("parametre1","booking", "parametre2",1);
        Response response = given().accept("application/json").spec(spec02)
                .when().get("/{parametre1}/{parametre}");
        HashMap<String,Object> actualData= response.as(HashMap.class);

        Assert.assertEquals(actualData.get("firstname"),expectedData.get("firstname "));
        Assert.assertEquals(actualData.get("lastname"),expectedData.get("lastname"));
        Assert.assertEquals(actualData.get("totalprice"),expectedData.get("totalprice"));
        Assert.assertEquals(actualData.get("depositpaid"),expectedData.get("depositpaid"));
        Assert.assertEquals(((HashMap)actualData.get("bookingdates")).get("checkin"),
                ((HashMap)expectedData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((HashMap<?, ?>) actualData.get("bookingdates")).get("checkout"),
                ((HashMap<?, ?>) expectedData.get("bookingdates")).get("checkout"));

        //2.yöntem
        JsonPath jsonPath =response.jsonPath();

        Assert.assertEquals(expectedData.get("firstname"),jsonPath.getString("fristname"));
        Assert.assertEquals(expectedData.get("lastname"),jsonPath.getString("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),jsonPath.getString("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),jsonPath.getString("depositpaid"));
        Assert.assertEquals(((HashMap)expectedData.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals(((HashMap)expectedData.get("bookingdates")).get("checkout"),
                jsonPath.getString("bookingdates.checkout"));


    }

}
