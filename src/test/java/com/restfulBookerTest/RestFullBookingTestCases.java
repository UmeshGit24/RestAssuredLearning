package com.restfulBookerTest;

import com.data.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**

Created by  @Umesh Joshi
 */

public class RestFullBookingTestCases extends BaseSetUp{

    /*
    TestCases  Description
     */
    private final  static  String VERIFY_CREATE_BOOKING_API="Verify create booking API response code ,data send  and store return booking id by API";
    private final static String VERIFY_BOOKING_DETAILS_BASED_ON_BOOKING_ID="Verify API response code and booking details based upon the booking Id";
    private final static  String VERIFY_BOOKING_ID_BASED_ON_FIRST_NAME="Verify API response code and booking Id based upon the firstname ";
    private final static  String VERIFY_UPDATE_BOOKING="Verify update details  of a already booking and also verify response code and update booking details";
    private final static String VERIFY_PARTIAL_BOOKING_UPDATE="Verify partial booking details are updated and verify the booking data after Partial Booking updated";
    private final static  String VERIFY_BOOKING_DELETE="Verify Booking Delete API response code";
    private final static  String VERIFY_SEARCHING_DELETED_BOOKING_DETAILS="Verify search already deleted Booking and verify response cocde";


    private AuthenticationTokenData authenticationData;
    private BookingData newBooking;
    private PartialBookingData partialBookingData;
    private BookingData updateBookingData;
    private int bookingId;

    @BeforeTest
    public void beforeTestSetUp(){
        AuthenticationTokenDataBuilder authDataBuilder=new AuthenticationTokenDataBuilder();
        BookingDataBuilder bookDataBuilder=new BookingDataBuilder();
        newBooking=bookDataBuilder.bookingDataBuilder();
        authenticationData=authDataBuilder.authenticationDataBuilder();
        partialBookingData=bookDataBuilder.partialBookingDataBuilder();
        updateBookingData=bookDataBuilder.bookingDataBuilder();

    }

    @Test(description = VERIFY_CREATE_BOOKING_API,priority = 1)
    public void createBooking(){
        System.out.println("*********"+newBooking);
        bookingId= given()
                .body(newBooking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("bookingid",notNullValue())
                .body("booking.firstname",equalTo(newBooking.getFirstname()))
                .body("booking.lastname",equalTo(newBooking.getLastname()))
                .body("booking.totalprice",equalTo(newBooking.getTotalprice()))
                .body("booking.depositpaid",equalTo(newBooking.isDepositpaid()))
                .body("booking.bookingdates.checkin",equalTo(newBooking.getBookingdates().getCheckin()))
                .body("booking.bookingdates.checkout",equalTo(newBooking.getBookingdates().getCheckout()))
                .body("booking.additionalneeds",equalTo(newBooking.getAdditionalneeds()))
                .extract()
                .path("bookingid");

    }
    @Test(description = VERIFY_BOOKING_DETAILS_BASED_ON_BOOKING_ID,priority = 2,dependsOnMethods = {"createBooking"})
    public void getBookingDetails(){
        given()
                .get("/booking/"+bookingId)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("firstname",equalTo(newBooking.getFirstname()))
                .body("lastname",equalTo(newBooking.getLastname()))
                .body("totalprice",equalTo(newBooking.getTotalprice()))
                .body("depositpaid",equalTo(newBooking.isDepositpaid()))
                .body("bookingdates.checkin",equalTo(newBooking.getBookingdates().getCheckin()))
                .body("bookingdates.checkout",equalTo(newBooking.getBookingdates().getCheckout()))
                .body("additionalneeds",equalTo(newBooking.getAdditionalneeds()));


    }

    @Test(description = VERIFY_BOOKING_ID_BASED_ON_FIRST_NAME,priority = 3,dependsOnMethods ={"createBooking"})
    public void getBookingID(){
        given()
                .get("/booking?firstname="+newBooking.getFirstname())
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("bookingid",hasItem(bookingId));


    }


    @Test(description = VERIFY_UPDATE_BOOKING,priority =4,dependsOnMethods ={"createBooking"})
    public void updateBooking(){
        given().body(updateBookingData)
                .when()
                .header("Cookie","token=" + generateAuthorisationToken())
                .put("/booking/"+bookingId)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("firstname",equalTo(updateBookingData.getFirstname()))
                .body("lastname",equalTo(updateBookingData.getLastname()))
                .body("totalprice",equalTo(updateBookingData.getTotalprice()))
                .body("depositpaid",equalTo(updateBookingData.isDepositpaid()))
                .body("bookingdates.checkin",equalTo(updateBookingData.getBookingdates().getCheckin()))
                .body("bookingdates.checkout",equalTo(updateBookingData.getBookingdates().getCheckout()))
                .body("additionalneeds",equalTo(updateBookingData.getAdditionalneeds()));

    }


    @Test(description = VERIFY_PARTIAL_BOOKING_UPDATE,priority =5,dependsOnMethods = {"updateBooking"})
    public void updatePartialBooking(){
        given().body(partialBookingData)
                .header("Cookie","token="+generateAuthorisationToken())
                .when()
                .patch("/booking/"+bookingId)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("firstname",equalTo(partialBookingData.getFirstname()))
                .body("lastname",equalTo(updateBookingData.getLastname()))
                .body("totalprice",equalTo(partialBookingData.getTotalprice()))
                .body("depositpaid",equalTo(updateBookingData.isDepositpaid()))
                .body("bookingdates.checkin",equalTo(updateBookingData.getBookingdates().getCheckin()))
                .body("bookingdates.checkout",equalTo(updateBookingData.getBookingdates().getCheckout()))
                .body("additionalneeds",equalTo(partialBookingData.getAdditionalneeds()));

    }

    @Test(description = VERIFY_BOOKING_DELETE,priority =6,dependsOnMethods = {"createBooking"})
    public void deleteBooking(){
        given()
                .header("Cookie","token="+generateAuthorisationToken())
                .when()
                .delete("/booking/"+bookingId)
                .then()
                .statusCode(201);


    }

    @Test(description = VERIFY_SEARCHING_DELETED_BOOKING_DETAILS,priority =7,dependsOnMethods = "deleteBooking")
    public void verifyDeletedBooking(){
        given()
                .get("/booking/"+bookingId)
                .then()
                .statusCode(404);


    }


    private String generateAuthorisationToken(){
       final String authorisationToken= given().body(authenticationData)
                .post("/auth")
                .then()
                .statusCode(200)
               .and()
               .assertThat()
               .body("token",notNullValue())
               .extract()
               .path("token");

       return authorisationToken;

    }

}
