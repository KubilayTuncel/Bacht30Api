package com.techpro.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest01 {
    //https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde
    // donecek cevap(response) icin
    //HTTP status kodunun 200
    //Content Type'in Json
    //Ve Status Line'in HTTP/1.1 200 OK
    //Oldugunu test edin
    //Status Code : 200
    //Status Content : application/json; charset=utf-8
    //Status Line HTTP/1.1 200 OK

    @Test
    public void test01(){
        //1- Api test yaparken ilk once url belirlenmeli
        String url = "https://restful-booker.herokuapp.com/booking/3";
        //2-beklenen sonuc (expected result) olusturulur.
        //bu case de benden body dogrulamasi istenmedigi icin simdilik beklenen sonuc olusturmuyoruz

        //3-request gondermek
        Response  response =given().accept("application/json").when().get(url);
        response.prettyPrint();
        //4-actual result olusturulur


        //5-dogrulama yap (assertion)
        System.out.println("Status Code : "+response.getStatusCode());
        System.out.println("Status Content : "+ response.getContentType());
        System.out.println("Status Line "+ response.getStatusLine());
        System.out.println("Headers : "+response.getHeaders());

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());
        Assert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());

        //response tool'u ile assert edebiliyoruz bu sekilde

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK");


    }


}
