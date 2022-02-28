package com.techpro.day06;

import com.techpro.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummyTestBase {
//http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
//   1) Butun calisanlarin isimlerini consola yazdıralim
//   2) 3. calisan kisinin ismini konsola yazdıralim
//   3) Ilk 5 calisanin adini konsola yazdiralim
//   4) En son calisanin adini konsola yazdiralim

    @Test
    public void test(){

        spec03.pathParam("parametre1","employees");
        Response response =given().accept("application/json").spec(spec03).when()
                .get("/{parametre1}");

        //response.prettyPrint();
        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getList("data.employee_name"));
        System.out.println(jsonPath.getString("data.employee_name"));
        System.out.println(jsonPath.getString("data[2].employee_name"));
        System.out.println(jsonPath.getString("data.employee_name[2]"));
        for (int i=0; i<5;i++){
            System.out.println(jsonPath.getString("data["+i+"].employee_name"));
        }
        System.out.println(jsonPath.getString("data[-1].employee_name"));

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("Ashton Cox",jsonPath.getString("data[2].employee_name"));
        Assert.assertEquals("Doris Wilder",jsonPath.getString("data[-1].employee_name"));
        List<Integer> list=new ArrayList<Integer>();
        list.add(21);
        list.add(23);
        list.add(61);

        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(list));

    }
}
