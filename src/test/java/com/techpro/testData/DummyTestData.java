package com.techpro.testData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
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

    public HashMap<String, Object> setupTestData() {
        HashMap<String, Object> calisanBilgileri = new HashMap<>();
        calisanBilgileri.put("id", "11");
        calisanBilgileri.put("employee_name", "Jena Gaines");
        calisanBilgileri.put("employee_age", "30");
        calisanBilgileri.put("profile_image", "");

        List<Integer> yaslar = new ArrayList<>();

        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("calisanBilgileri", calisanBilgileri);
        expectedData.put("besinciCalisan", "Airi Satou");
        expectedData.put("sondanIkinciCalisanMaasi", 106450);
        expectedData.put("arananyaslar", yaslar);

        return expectedData;
    }
    public HashMap<String,Object> setUpTestData2(){
        HashMap<String,Object> expectedData=new HashMap<String, Object>();
        expectedData.put("statusCode",200);
        expectedData.put("enYuksekMaas",725000);
        expectedData.put("enKucukYas",19);
        expectedData.put("ikinciEnYuksekMaas",675000);
        return expectedData;
    }

    public HashMap<String, String> setupRequestBody(){
        HashMap<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", "batch30");
        requestBody.put("salary","123000");
        requestBody.put("age","20");

        return requestBody;
    }

    public HashMap<String, Object> setupExpectedData(){

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", "batch30");
        data.put("salary","123000");
        data.put("age","20");

        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("statusCode",200);
        expectedData.put("status","success");
        expectedData.put("data",data);
        expectedData.put("message","Successfully! Record has been added.");
        return expectedData;
    }

    public JSONObject setDelete(){
        /*{
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
}

         */
        JSONObject deleteData = new JSONObject();
        deleteData.put("status", "success");
        deleteData.put("data", "2");
        deleteData.put("message", "Successfully! Record has been deleted");
        return deleteData;
    }
}
