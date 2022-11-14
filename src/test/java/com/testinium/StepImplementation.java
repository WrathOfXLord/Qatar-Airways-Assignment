package com.testinium;

import java.time.Duration;

// import com.thoughtworks.gauge.Gauge;
// import com.thoughtworks.gauge.Step;

// import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import com.thoughtworks.gauge.Step;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;



public class StepImplementation extends BaseTest {
    @Step("Wait for <seconds> seconds")
    public void waitForSeconds(double seconds) throws InterruptedException {
        Thread.sleep((long) (seconds * 1000));
    }

    @Step("Click the element with <xpath> xpath")
    public void clickElementByXpath(String xpath) {
        MobileElement element = appiumDriver.findElement(By.xpath(xpath));
        element.click();
        System.out.println("Clicked the element with " + xpath + " xpath.");
    }

    @Step("Click the element with <id> id")
    public void clickElementById(String id) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        element.click();
        System.out.println("Clicked the element with " + id + " id.");
    }

    @Step("Click accept button")
    public void clickAcceptButton() throws InterruptedException {
        // TODO: fill id
        MobileElement element = appiumDriver.findElement(By.id(""));
        if(element.isDisplayed()) {
            element.click();
            waitForSeconds(3);
        } else {
            System.out.println("Pop-up didn't appear !");
        }
    }

    @Step("Check if the element with <xpath> xpath contains <expectedResult> text")
    public void assertElement(String xpath, String expectedResult) {
        MobileElement element = appiumDriver.findElement(By.xpath(xpath));
        System.out.println("Retrieved text == " + element.getText());
        Assert.assertTrue("Element not found !", element.getText().equals(expectedResult));
    }

    @Step("Swipe <direction>")
    public void swipeScreen(Direction direction) {
        final int ANIMATION_TIME = 500; //ms
        final int PRESS_TIME = 1000;     //ms
        int edgeBorder = 30;            //padding
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dimension = appiumDriver.manage().window().getSize();
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
            new TouchAction(appiumDriver)
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

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(StepImplementation.class);
        logger.info("Hello world");
    }

}
