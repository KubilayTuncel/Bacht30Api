package com.techpro.day09;

import com.techpro.testBase.DummyTestBase;
import com.techpro.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13JsonPath extends DummyTestBase {
    //Status kodun 200 olduğunu,
    //5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
    //Sondan 2. çalışanın maaşının 106450 olduğunu
    //40,21 ve 19 yaslarında çalışanlar olup olmadığını
    //11. Çalışan bilgilerinin
    //  {
    // “id”:”11”
    // "employee_name": "Jena Gaines",
    //"employee_salary": "90560",
    //"employee_age": "30",
    //"profile_image": "" }
    //} gibi olduğunu test edin.
    @Test
    public void test(){
        spec03.pathParam("parametre", "employees");
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedData = expectedObje.setupTestData();
        Response response = given().accept("application/json").spec(spec03).when()
                .get("/{parametre}");

        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedData.get("besinciCalisan"),jsonPath.getString("data[4].employee_name"));
        Assert.assertEquals(expectedData.get("calisansayisi"),jsonPath.getList("data.id").size());
        Assert.assertEquals(expectedData.get("sondanIkinciCalisanMaasi"),jsonPath.getInt("data[-2].employee_salary"));
        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll((List)expectedData.get("aranayaslar")));
        Assert.assertEquals(((Map)expectedData.get("calisanBilgileri")).get("employee_name"),
                jsonPath.getString("data[10].employee_name"));
        Assert.assertEquals(((Map)expectedData.get("calisanBilgileri")).get("employee_age"),
                jsonPath.getInt("data[10].employee_age"));
        Assert.assertEquals(((Map)expectedData.get("calisanBilgileri")).get("employee_salary"),
                jsonPath.getInt("data[10].employee_salary"));
        Assert.assertEquals(((Map)expectedData.get("calisanBilgileri")).get("profil_image"),
                jsonPath.getString("data[10].profil_image"));
    }
}
