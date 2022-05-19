package com.data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 Created By @Umesh Joshi
 **/

public class BookingDataBuilder {

    public BookingData bookingDataBuilder(){
        Faker faker=new Faker();
        SimpleDateFormat formatter=new SimpleDateFormat("YYYY-MM-dd");

        return BookingData.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(100,5000))
                .depositpaid(true)
                .bookingdates(BookingDates.builder()
                        .checkin(formatter.format(faker.date()
                                .future(4, TimeUnit.DAYS)))
                        .checkout(formatter.format(faker.date()
                                .future(6,TimeUnit.DAYS)))
                        .build())
                .additionalneeds(faker.food().fruit())
                .build();


    }

    public PartialBookingData partialBookingDataBuilder(){

        Faker faker =Faker.instance();
        return  PartialBookingData.builder()
                .firstname(faker.name().firstName())
                .totalprice(faker.number().numberBetween(200,4000))
                .additionalneeds(faker.food().sushi())
                .build();



    }




}
