package com.techpro.day09;

import com.techpro.testBase.DummyTestBase;
import com.techpro.testData.DummyTestData;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestMatchers extends DummyTestBase {
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
            public void test() {
        spec03.pathParam("parametre", "employees");
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedData = expectedObje.setupTestData();
        Response response = given().accept("application/json").spec(spec03).when()
                .get("/{parametre}");

        response.then().assertThat().statusCode((Integer)expectedData.get("statusCode"))
                .body("data[4].employee_name",equalTo(expectedData.get("besinciCalisan"))
                        ,"data.id",hasSize((Integer)expectedData.get("calisanSayisi"))
                        ,"data[-2].employee_salary",equalTo(expectedData.get("sondaIkinciCalisanMaasi"))
                        ,"data.employee_age",hasItems(((List)expectedData.get("arananyaslar")).get(0),
                                ((List<?>) expectedData.get("arananyaslar")).get(1),
                                ((List<?>) expectedData.get("arananyaslar")).get(2))
                        ,"data[10].employee_name",equalTo(((Map)expectedData.get("calisanBilgileri")).get("employee_name"))
                        ,"data[10].employee_salary",equalTo(((Map<?, ?>) expectedData.get("calisanBilgileri")).get("employee_salary"))
                        ,"data[10].employee_age",equalTo(((Map<?, ?>) expectedData.get("calisanBilgileri")).get("employee_age"))
                        ,"data[10].profile_image",equalTo(((Map<?, ?>) expectedData.get("calisanBilgileri")).get("profil_image")));





    }

}
