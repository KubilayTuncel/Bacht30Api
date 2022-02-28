package com.techpro.day08;

import com.techpro.testBase.DummyTestBase;
import com.techpro.testData.DummyTestData;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyTestBase {
    //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
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

        spec03.pathParam("parametre","employees");
        DummyTestData expectedObje= new DummyTestData();
        HashMap<String, Object> expectedData = expectedObje.setupTestData();
        Response response = given().accept("application/json").spec(spec03).when()
                .get("/{parametre}");

        HashMap<String, Object> actualData = response.as(HashMap.class);

        System.out.println(actualData);
        //bu yöntemin adi disvilizaysin

        Assert.assertEquals(expectedData.get("statusCode"),response.getStatusCode());
        //5. Çalışan isminin "Airi Satou" olduğunu ,
        Assert.assertEquals(expectedData.get("besinciCalisan"),
                ((Map)((List)actualData.get("data")).get(4)).get("employee_name"));
        //çalışan sayısının 24 olduğunu,
        Assert.assertEquals(expectedData.get("calisanSayisi"), ((List<?>) actualData.get("data")).size());

        //Sondan 2. çalışanın maaşının 106450 olduğunu
        int dataSize=((List<?>) actualData.get("data")).size();

        Assert.assertEquals(expectedData.get("sondanIkinciCalisanMaasi"),
                ((Map)((List<?>) actualData.get("data")).get(dataSize-2)).get("employee_salary"));

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını

        List<Integer> actualYaslarListesi =new ArrayList<Integer>();

        for (int i =0; i<dataSize;i++) {
            actualYaslarListesi.add((Integer)((Map)((List)actualData.get("data")).get(i)).get("employee_age"));
        }

        Assert.assertTrue(actualYaslarListesi.containsAll((List)expectedData.get("arananyaslar")));

        Assert.assertEquals(((Map)expectedData.get("calisanBilgileri")).get("employee_name"),
                ((Map)((List<?>) actualData.get("data")).get(10)).get("employee_name"));

        Assert.assertEquals(((Map<?, ?>) expectedData.get("calisanBilgileri")).get("employee_salary"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(((Map<?, ?>) expectedData.get("calisanBilgileri")).get("employee_age"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_age"));

        Assert.assertEquals(((Map<?, ?>) expectedData.get("calisanBilgileri")).get("profil_image"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("profil_image"));




    }
}
