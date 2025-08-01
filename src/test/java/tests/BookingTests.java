package tests;

import org.testng.annotations.Test;
import pojos.BookingDates;
import pojos.BookingInfo;

public class BookingTests {


    @Test
    public void verifyCreateBooking() {

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-06-01");
        bookingDates.setCheckout("2025-06-02");

        BookingInfo bookingInfo = new BookingInfo();
        bookingInfo.setFirstname("Jessica");
        bookingInfo.setLastname("Alba");
        bookingInfo.setTotalprice(999);
        bookingInfo.setDepositpaid(true);
        bookingInfo.setAdditionalneeds("blanket");


    }
}
