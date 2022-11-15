package com.testinium.Steps;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.thoughtworks.gauge.Step;

import io.appium.java_client.MobileElement;

public class HomeSteps extends PageBase {
    
    @Step("Click location skip")
    public void skipLocation() {
        methods.clickElementById("com.m.qr:id/skip_button");
        logger.info("Location services skipped");
    }

    @Step("Skip greetings message")
    public void skipGreetings() {
        methods.clickElementById("com.m.qr:id/onboarding_skip_button");
        logger.info("Greetings message skipped");
    }

    @Step("Reject offer")
    public void rejectOffer() {
        methods.clickElementById("com.m.qr:id/push_consent_decline");
        logger.info("Offer rejected.");
    }

    @Step("Dissmiss information message")
    public void dismissMessage() {
        try {
            methods.clickElementById("com.m.qr:id/secondary_button");
            logger.info("Information message dismissed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Step("Check if the application is running")
    public void checkApplicationStatus() {
        MobileElement element = methods.findElement(By.id("com.m.qr:id/rvmp_home_inspiration_title"));
        logger.info("Screen title: " + element.getText().trim());
        logger.info("The app is running.");
        Assert.assertEquals("Texts are different, the app is not running.", element.getText().trim(), "Travel Inspiration");
    }

    @Step("Go to booking page")
    public void gotoBookingPage() {
        methods.clickElementByAccessibilityId("Book");
        logger.info("Current page: Booking page." );
    }
}
