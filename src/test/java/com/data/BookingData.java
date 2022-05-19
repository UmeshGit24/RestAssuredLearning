package com.data;

import lombok.Builder;
import lombok.Data;

/**
 Created By @Umesh Joshi
 **/

@Data
@Builder
public class BookingData {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

}
