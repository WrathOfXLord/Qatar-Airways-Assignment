package com.testinium.Steps;

import java.time.Duration;
import java.util.List;
import java.util.Random;

// import com.thoughtworks.gauge.Gauge;
// import com.thoughtworks.gauge.Step;

// import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.testinium.BaseTest;
import com.thoughtworks.gauge.Step;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;



public class StepImplementation extends BaseTest {

    String time;

    @Step("Wait for <seconds> seconds")
    public void waitForSeconds(double seconds) throws InterruptedException {
        Thread.sleep((long) (seconds * 1000));
    }

    @Step("Click the element with <accessibilityId> accessibility id")
    public void clickElementByAccessibilityId(String accessibilityId) {
        MobileElement element = driver.findElementByAccessibilityId(accessibilityId);
        element.click();
        logger.info("Clicked the element with " + accessibilityId + " accessibilityId.");
    }

    @Step("Click the element with <xpath> xpath")
    public void clickElementByXpath(String xpath) {
        MobileElement element = driver.findElement(By.xpath(xpath));
        element.click();
        logger.info("Clicked the element with " + xpath + " xpath.");
    }

    @Step("Click the element with <id> id")
    public void clickElementById(String id) {
        MobileElement element = driver.findElement(By.id(id));
        element.click();
        logger.info("Clicked the element with " + id + " id.");
    }

    @Step("Send <key> text to the element with <id> id")
    public void sendKeysById(String key, String id) {
        MobileElement element = driver.findElement(By.id(id));
        element.sendKeys(key);
        logger.info("Sent " + key + " text to the element with " + id + " id.");
    }
    
    @Step("Send <key> text to the element with <xpath> xpath")
    public void sendKeysByXpath(String key, String xpath) {
        MobileElement element = driver.findElement(By.xpath(xpath));
        element.sendKeys(key);
        logger.info("Sent " + key + " text to the element with " + xpath + " xpath.");
    }

    @Step("Send <key> text to the element with <accessibilityId> accessibility id")
    public void sendKeysByAccessibilityId(String key, String accessibilityId) {
        MobileElement element = driver.findElementByAccessibilityId(accessibilityId);
        element.sendKeys(key);
        logger.info("Sent " + key + " text to the element with " + accessibilityId + " accessibilityId.");
    }

    @Step("Click accept button")
    public void clickAcceptButton() throws InterruptedException {
        // TODO: fill id
        MobileElement element = driver.findElement(By.id(""));
        if(element.isDisplayed()) {
            element.click();
            waitForSeconds(3);
        } else {
            System.out.println("Pop-up didn't appear !");
        }
    }

    @Step("Check if the element with <xpath> xpath contains <expectedResult> text")
    public void assertElement(String xpath, String expectedResult) {
        MobileElement element = driver.findElement(By.xpath(xpath));
        System.out.println("Retrieved text == " + element.getText());
        Assert.assertTrue("Element not found !", element.getText().equals(expectedResult));
    }

    @Step("Swipe <direction>")
    public void swipeScreen(Direction direction) {
        final int ANIMATION_TIME = 500; //ms
        final int PRESS_TIME = 1000;     //ms
        int edgeBorder = 30;            //padding
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dimension = driver.manage().window().getSize();
        pointOptionStart = PointOption.point(dimension.width / 2, dimension.height / 2);
        switch (direction) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dimension.width / 2, dimension.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dimension.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dimension.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dimension.width - edgeBorder, dimension.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + direction + "' NOT supported");
        }

        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
            System.out.println("Swipe " + direction);
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
    
        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @Step("Check if the application is running")
    public void checkApplicationStatus() {
        WebElement element = driver.findElement(By.id("com.m.qr:id/rvmp_home_inspiration_title"));
        logger.info("Screen title: " + element.getText().trim());
        logger.info("The app is running.");
        Assert.assertEquals("Texts are different, the app is not running.", element.getText().trim(), "Travel Inspiration");
    }

    @Step("Reject offer")
    public void rejectOffer() {
        driver.findElementById("com.m.qr:id/push_consent_decline").click();
        logger.info("Offer rejected.");
    }

    @Step("Select a date")
    // com.m.qr:id/rvmp_flight_number
    // 
    public void selectADate() {
        driver.findElementByXPath("//*[@text='20']").click();
        logger.info("Selected 20th day of the month.");
    }

    @Step("Select airport <at> from the list")
    public void selectAirport(int at) {
        driver.findElementsByXPath("//*[@resource-id='com.m.qr:id/rvmp_item_ond_selection_list_iata_code_and_airport_name']")
              .get(at - 1)
              .click();
        logger.info("Selected airport from the list: " + at + ".");
    }

    @Step("Select random flight")
    public void selectRandomFlight() {
        List<MobileElement> elements = driver.findElementsByXPath("//*[@resource-id='com.m.qr:id/rvmp_flight_number']");
        List<MobileElement> timeElements = driver.findElementsByXPath("//*[@resource-id='com.m.qr:id/rvmp_departure_time']");
        Random rand = new Random();
        int at = rand.nextInt(elements.size());
        time = timeElements.get(at).getText();
        elements.get(at).click();
        logger.info("Selected flight from the list: " + (at + 1));
        logger.info("");
    }

    @Step("Verify flight selection screen")
    public void verifyScreen() {
        MobileElement element = driver.findElementById("com.m.qr:id/rvmp_results_count");
        Assert.assertTrue("Results are not listed. Error !", element.getText().trim().contains("results"));
        logger.info("Flight selection screen: " + element.getText());
    }


    @Step("Check if the times are equal")
    public void checkTimes() {
        MobileElement element = driver.findElementById("com.m.qr:id/from_time");
        Assert.assertEquals("Time values are different. Error !", element.getText(), time);
    }

}
