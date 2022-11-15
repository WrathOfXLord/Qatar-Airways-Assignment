package com.testinium.Steps;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.thoughtworks.gauge.Step;

import io.appium.java_client.MobileElement;

public class FlightSteps extends PageBase {
    String time;
    @Step("Verify flight selection screen")
    public void verifyScreen() {
        MobileElement element = methods.findElement(By.id("com.m.qr:id/rvmp_results_count"));
        Assert.assertTrue("Results are not listed. Error !", element.getText().trim().contains("results"));
        logger.info("Flight selection screen: " + element.getText());
    }

    @Step("Select random flight")
    public void selectRandomFlight() {
        List<MobileElement> elements = methods.findElements(By.xpath("//*[@resource-id='com.m.qr:id/rvmp_flight_number']"));
        List<MobileElement> timeElements = methods.findElements(By.xpath("//*[@resource-id='com.m.qr:id/rvmp_departure_time']"));
        Random rand = new Random();
        int at = rand.nextInt(elements.size());
        time = timeElements.get(at).getText();
        elements.get(at).click();
        logger.info("Selected flight from the list: " + (at + 1));
    }

    @Step("Confirm flight")
    public void confirmFlight() {
        methods.clickElementById("com.m.qr:id/rvmp_activity_flight_details_select_button");
        logger.info("Flight confirmed.");
    }

    @Step("Check if the times are equal")
    public void checkTimes() {
        MobileElement element = methods.findElement(By.id("com.m.qr:id/from_time"));
        Assert.assertEquals("Time values are different. Error !", element.getText(), time);
        logger.info("Flight time: " + element.getText() + ".");
    }
}
