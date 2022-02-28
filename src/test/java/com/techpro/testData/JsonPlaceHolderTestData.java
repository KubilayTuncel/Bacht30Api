package com.techpro.testData;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
  public Map<String,Object> setup(){
      HashMap<String,Object> expectedData = new HashMap<String, Object>();

      expectedData.put("statusCode",200);
      expectedData.put("Via","1.1 vegur");
      expectedData.put("Server","cloudflare");
      expectedData.put("userId",1);
      expectedData.put("title","quis ut nam facilis et officia qui");
      expectedData.put("completed",false);
      return expectedData;
    }

    public JSONObject setupExpectedDAta(){
      /* }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }

       */
      JSONObject expectedData = new JSONObject();
        expectedData.put("statusCode",201);
      expectedData.put("userId", 55);
      expectedData.put("title", "Tidy your room");
      expectedData.put("completed", false);
      return  expectedData;
    }

    /*  {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }

     */

    public JSONObject setupPut(){
        JSONObject expetedData=new JSONObject();
        expetedData.put("userId", 21);
        expetedData.put("title", "Wash the dishes");
        expetedData.put("completed", false);
        return expetedData;
    }

    /*{
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}
   */
    public JSONObject requestPatch(){
        JSONObject requestPatch= new JSONObject();
        requestPatch.put("title", "API calismaliyim");
        return requestPatch;
    }

    public JSONObject setPatch(){
        JSONObject expectedData= new JSONObject();
        expectedData.put("userId", 10);
        expectedData.put("title", "API calismaliyim");
        expectedData.put("completed", true);
        expectedData.put("id", 198);
        return expectedData;
    }
}
