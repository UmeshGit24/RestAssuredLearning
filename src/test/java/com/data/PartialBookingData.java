package com.data;

import lombok.Builder;
import lombok.Data;

/**
 Created By @Umesh Joshi
 **/

@Data
@Builder
public class PartialBookingData {

    private  String firstname;
    private  int totalprice;
    private String additionalneeds;

}
