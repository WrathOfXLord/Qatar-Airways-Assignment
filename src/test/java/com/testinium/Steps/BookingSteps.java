package com.testinium.Steps;

import org.openqa.selenium.By;

import com.thoughtworks.gauge.Step;

public class BookingSteps extends PageBase {
    @Step("Select <type> trip")
    public void selectTripType(String type) {
        methods.clickElementByAccessibilityId(type);
        logger.info("Trip type: " + type + ".");
    }

    @Step("Enter <location> as departure")
    public void enterDeparture(String location) throws InterruptedException {
        methods.clickElementById("com.m.qr:id/rvmp_fragment_rtow_flight_selection_origin_holder");
        methods.waitForSeconds(3);
        methods.sendKeysById(location, "com.m.qr:id/rvmp_fragment_ond_selection_filter_edittext");
        logger.info("Airports listed for " + location + ".");
    }

    @Step("Select airport <at> from the list")
    public void selectAirport(int at) {
        methods.findElements(By.xpath("//*[@resource-id='com.m.qr:id/rvmp_item_ond_selection_list_iata_code_and_airport_name']"))
              .get(at - 1)
              .click();
        logger.info("Selected airport from the list: " + at + ".");
    }

    @Step("Enter <location> as destination")
    public void enterDestination(String location) throws InterruptedException {
        methods.clickElementById("com.m.qr:id/rvmp_fragment_rtow_flight_selection_destination_holder");
        methods.waitForSeconds(3);
        methods.sendKeysById(location, "com.m.qr:id/rvmp_fragment_ond_selection_filter_edittext");
        logger.info("Airports listed for " + location + ".");
    }

    @Step("Select date: <day>")
    public void selectADate(int day) throws InterruptedException {
        methods.clickElementById("com.m.qr:id/rvmp_fragment_rtow_flight_selection_date_holder_date_text_view");
        methods.waitForSeconds(4);
        methods.clickElementByXpath("//*[@text='" + day + "']");
        logger.info("Selected " + day + "th day of the month.");
        methods.waitForSeconds(4);
        methods.clickElementById("com.m.qr:id/fragment_calendar_confirm_button");
    }

    @Step("Select cabin class economy")
    public void selectEconomy() throws InterruptedException {
        methods.clickElementById("com.m.qr:id/fragment_rtow_flight_cabin_class_text_view");
        methods.waitForSeconds(4);
        methods.clickElementById("com.m.qr:id/cabin_selection_economy_row");
        logger.info("Economy class selected.");
    }

    @Step("Confirm flight options")
    public void clickConfirm() {
        methods.clickElementById("com.m.qr:id/rvmp_booking_search_flights_button");
        logger.info("Searching flights...");
    }
}
