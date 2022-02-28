package com.techpro.pojos;

public class BookingDatesPojo {
    /*"bookingdates": {
                                 "checkin": "2020-09-09",
                                 "checkout": "2020-09-21"
                             }

     */
    private String checkin;
    private String checkout;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
    //3 adim constructor olustur parametreli ve parametresiz

    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
    //4- toString methodu olustur

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
