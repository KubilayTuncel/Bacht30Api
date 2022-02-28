package com.techpro.day10;

import com.techpro.testBase.HerokuAppTEstBase;
import com.techpro.testData.HerokuappTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerokuAppTEstBase {
    //https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
    //{ "firstname": "Selim",
    //               "lastname": "Ak",
    //               "totalprice": 11111,
    //               "depositpaid": true,
    //               "bookingdates": {
    //                   "checkin": "2020-09-09",
    //                   "checkout": "2020-09-21"
    //                }
    // }gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
    // "booking": {
    //         "firstname": " Selim ",
    //         "lastname": " Ak ",
    //         "totalprice":  11111,
    //         "depositpaid": true,
    //         "bookingdates": {
    //             "checkin": "2020-09-01",
    //              "checkout": " 2020-09-21”
    //         },
    //        }
    //olduğunu test edin

    @Test
    public void test(){
        spec02.pathParam("parametre","booking");
        HerokuappTestData testData = new HerokuappTestData();
        JSONObject expetedData= testData.setUpTestAndRequestData();

        Response response = given().accept(ContentType.JSON).spec(spec02).auth()
                .basic("admin","password123").body(expetedData.toString()).when().post("/{parametre}");
        response.prettyPrint();

        //De Serialization yontemi

        HashMap<String, Object> actualData = response.as(HashMap.class);

        System.out.println(actualData);
        Assert.assertEquals(expetedData.getString("firstname"),((Map)actualData.get("booking")).get("firstname"));
        Assert.assertEquals(expetedData.getString("lastname"),((Map<?, ?>) actualData.get("booking")).get("lastname"));
        Assert.assertEquals(expetedData.getInt("totalprice"),((Map<?, ?>) actualData.get("booking")).get("totalprice"));
        Assert.assertEquals(expetedData.getBoolean("depositpaid"),((Map<?, ?>) actualData.get("booking")).get("depositpaid"));
        Assert.assertEquals(expetedData.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map<?, ?>) actualData.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(expetedData.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map<?, ?>) actualData.get("booking")).get("bookingdates")).get("checkout"));

        //JsonPath
        JsonPath jsonPath =response.jsonPath();
        Assert.assertEquals(expetedData.getString("lastname"),jsonPath.getString("booking.lastname"));
        Assert.assertEquals(expetedData.getString("firstname"),jsonPath.getString("booking.firstname"));
        Assert.assertEquals(expetedData.getInt("totalprice"),jsonPath.getInt("booking.totalprice"));
        Assert.assertEquals(expetedData.getBoolean("depositpaid"),jsonPath.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expetedData.getJSONObject("bookingdates").getString("checkin"),
                jsonPath.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expetedData.getJSONObject("bookingdates").getString("checkout"),
                jsonPath.getString("booking.bookingdates.checkout"));



    }
}
