package com.techpro.day09;
import com.techpro.testBase.DummyTestBase;
import com.techpro.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyTestBase {

    //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    //Status kodun 200 olduğunu,
    //En yüksek maaşın 725000 olduğunu,
    //En küçük yaşın 19 olduğunu,
    //İkinci en yüksek maaşın 675000
    //olduğunu test edin.

    @Test
            public void test() {
        spec03.pathParam("parametre", "employees");
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setUpTestData2();
        Response response = given().accept("application/json").spec(spec03).when()
                .get("/{parametre}");
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(expectedDataMap.get("statusCode"),(Integer)response.getStatusCode());
        //Maaslardan olusan bir list lazim
        List<Integer> actualMaasList=new ArrayList<>();
        //actualDataMapten donen datanin uzunlugunu bulmaliyim
        int dataSize=((List)actualDataMap.get("data")).size();
        //tum employeelerin maaslarini liste aktariyoruz
        for (int i = 0; i <dataSize ; i++) {
            actualMaasList.add((Integer) ((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(actualMaasList);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas")
                ,actualMaasList.get(actualMaasList.size()-1));
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas")
                ,actualMaasList.get(actualMaasList.size()-2));
        List<Integer>actualYasList=new ArrayList<>();
        for (int i = 0; i <dataSize ; i++) {
            actualYasList.add((Integer) ((Map)((List<?>)actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        Collections.sort(actualYasList);
        Assert.assertEquals(expectedDataMap.get("enKucukYas")
                ,actualYasList.get(0));
        JsonPath json=response.jsonPath();
        List<Integer> maasListesi=json.getList("data.employee_salary");
        Collections.sort(maasListesi);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasListesi.get(maasListesi.size()-1));
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"),maasListesi.get(maasListesi.size()-2));
        List<Integer> yasListesi=json.getList("data.employee_age");
        Collections.sort(yasListesi);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasListesi.get(0));




    }
}
