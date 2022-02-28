package com.techpro.day13;

import com.google.gson.Gson;
import com.techpro.pojos.Data;
import com.techpro.pojos.DummyPojo;
import com.techpro.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequestWithPojo01 extends DummyTestBase {
    /*http://dummy.restapiexample.com/api/v1/employee/1 url ‘ine bir get request gönderildiğinde , dönen response ‘un,
                           Status kodunun 200 ve response body’nin
                                {
                                 "status": "success",
                                 "data": {
                                     "id": 1,
                                     "employee_name": "Tiger Nixon",
                                     "employee_salary": 320800,
                                     "employee_age": 61,
                                     "profile_image": ""
                                 },
                                 "message": "Successfully! Record has been fetched."
                                }
Olduğunu test edin

     */

    @Test
    public void test(){
        spec03.pathParams("parametre1","employee","parametre2",1);
        Data data = new Data(1,"Tiger Nixon",320800,61,"");
        DummyPojo expectedData = new DummyPojo("success",data,"message");

        Response response =given().contentType(ContentType.JSON).spec(spec03)
                .when().get("/{parametre1}/{parametre2}");

        DummyPojo actualData = response.as(DummyPojo.class);
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.getStatus(),actualData.getStatus());
        Assert.assertEquals(expectedData.getMessage(),actualData.getMessage());
        Assert.assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        Assert.assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        Assert.assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        Assert.assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        Assert.assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());

        //Serializaztion java yapisinda olan datalari json formati dönüstürme islemidir
        //Gson sinifindan bir obje uretilir

        Gson gson = new Gson();
        String jsonFromJava = gson.toJson(actualData);
        System.out.println(jsonFromJava);
    }

}
