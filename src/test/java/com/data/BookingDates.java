package com.data;

import lombok.Builder;
import lombok.Data;

/**
 Created By @Umesh Joshi
 **/

@Data
@Builder
public class BookingDates {

    private String checkin ;
    private String checkout;

}
