package com.techpro.day07;

import com.techpro.testBase.DummyTestBase;
import io.restassured.response.Response;
import org.junit.Test;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10  extends DummyTestBase {
    /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde
Dönen response un
 Status kodunun 200,
 1)10’dan büyük tüm id’leri ekrana yazdırın ve
10’dan büyük 14 id olduğunu,
 2)30’dan küçük tüm yaşları ekrana yazdırın ve
  bu yaşların içerisinde en büyük yaşın 23 olduğunu
 3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
  bunların içerisinde “Charde Marshall” olduğunu test edin
     */
    @Test
    public void test(){
        spec03.pathParam("parametre1","employees");
        Response response= given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(200,response.getStatusCode());
        List<Integer> idList= jsonPath.getList("data.findAll{it.id>10}.id");
        System.out.println(idList);
        Assert.assertEquals(14,idList.size());
        List<Integer> yasListesi= jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yasListesi);
        Collections.sort(yasListesi);
        Assert.assertEquals((Integer)23,yasListesi.get(yasListesi.size()-1));

        //2.yöntem
        //int maxAge = ageList.stream().reduce(Integer.MIN_VALUE,Math::max);
        //
        //Assert.assertEquals(23,maxAge);


        List<String> nameList = jsonPath.getList("data.findAll{it.empoyee_salary>35000}.employee_name");
        System.out.println(nameList);
        Assert.assertTrue(nameList.contains("Charde Marshall"));
    }
}
