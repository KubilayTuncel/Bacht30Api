package com.techpro.day12;

import com.techpro.pojos.BookingDatesPojo;
import com.techpro.pojos.BookingIdPojo;
import com.techpro.pojos.BookingPojo;
import com.techpro.testBase.HerokuAppTEstBase;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRequestWithPojo02 extends HerokuAppTEstBase {
    /*https://restful-booker.herokuapp.com/booking
url’ine aşağıdaki request body gönderildiğinde,
 {
               "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 15000,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
}Status kodun 200 ve dönen response ‘un
   {
                         "bookingid": 11,
                         "booking": {
                             "firstname": "Selim",
                             "lastname": "Ak",
                             "totalprice": 15000,
                             "depositpaid": true,
                             "bookingdates": {
                                 "checkin": "2020-09-09",
                                 "checkout": "2020-09-21"
                             }
                         }
                      } olduğunu test edin

     */

    @Test
    public void test(){
        spec02.pathParam("parametre","booking");
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2020-09-09","2020-09-21");

        BookingPojo bookingPojo = new BookingPojo ("Selim","Ak",15000,true,bookingDatesPojo);

        Response response =given().accept("application/json").spec(spec02).auth()
                .basic("admin","password123").body(bookingPojo).when().post("/{parametre}");

        BookingIdPojo actualData = response.as(BookingIdPojo.class);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(bookingPojo.getFirstname(),actualData.getBooking().getFirstname());
        Assert.assertEquals(bookingPojo.getLastname(),actualData.getBooking().getLastname());
        Assert.assertEquals(bookingPojo.getTotalprice(),actualData.getBooking().getTotalprice());
        Assert.assertEquals(bookingPojo.isDepositpaid(),actualData.getBooking().isDepositpaid());
        Assert.assertEquals(bookingPojo.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(bookingPojo.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckout());

    }

}
