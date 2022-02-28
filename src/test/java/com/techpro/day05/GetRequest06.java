package com.techpro.day05;

import com.techpro.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class GetRequest06 extends JsonPlaceHolderTestBase {
    /*https://jsonplaceholder.typicode.com/todos/123 url'ine
   accept type'i "application/json" olan GET request'i yolladigimda
   gelen response’un
  status kodunun 200
    ve content type'inin "application/json"
  ve Headers'daki "Server" in "cloudflare"
  ve response body'deki "userId"'nin 7
  ve "title" in "esse et quis iste est earum aut impedit"
  ve "completed" bolumunun false oldugunu test edin

     */

    @Test
    public void test() {
        spec01.pathParams("name","todos","id",123);
        Response response = given().accept("application/json").spec(spec01).when().get("/{name}/{id}");

        response.prettyPrint();
        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON).header("Server", equalTo("cloudflare"))
                .body("userId", equalTo(7),
                        "title", equalTo("esse et quis iste est earum aut impedit"),
                        "completed",equalTo(false));
    }
}
