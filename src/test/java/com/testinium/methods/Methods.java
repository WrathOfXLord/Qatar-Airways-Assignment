package com.testinium.methods;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import com.testinium.BaseTest;
import com.thoughtworks.gauge.Step;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Methods {
    AppiumDriver<MobileElement> driver;
    public static Logger logger = LogManager.getLogger(Methods.class);
    public Methods() {
        driver = BaseTest.driver;
    }

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

    public MobileElement findElement(By by) {
        return driver.findElement(by);
    }

    public MobileElement findElementByAccessibilityId(String accessibilityId) {
        return driver.findElementByAccessibilityId(accessibilityId);
    }

    public List<MobileElement> findElementsByAccessibilityId(String accessibilityId) {
        return driver.findElementsByAccessibilityId(accessibilityId);
    }

    public List<MobileElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Step("Wait for <seconds> seconds")
    public void waitForSeconds(double seconds) throws InterruptedException {
        Thread.sleep((long) (seconds * 1000));
    }

    public void clickElementByAccessibilityId(String accessibilityId) {
        MobileElement element = findElementByAccessibilityId(accessibilityId);
        element.click();
        logger.info("Clicked the element with " + accessibilityId + " accessibilityId.");
    }

    public void clickElementByXpath(String xpath) {
        MobileElement element = findElement(By.xpath(xpath));
        element.click();
        logger.info("Clicked the element with " + xpath + " xpath.");
    }

    public void clickElementById(String id) {
        MobileElement element = findElement(By.id(id));
        element.click();
        logger.info("Clicked the element with " + id + " id.");
    }

    public void sendKeysById(String key, String id) {
        MobileElement element = findElement(By.id(id));
        element.sendKeys(key);
        logger.info("Sent " + key + " text to the element with " + id + " id.");
    }
    
    public void sendKeysByXpath(String key, String xpath) {
        MobileElement element = findElement(By.xpath(xpath));
        element.sendKeys(key);
        logger.info("Sent " + key + " text to the element with " + xpath + " xpath.");
    }

    public void sendKeysByAccessibilityId(String key, String accessibilityId) {
        MobileElement element = findElementByAccessibilityId(accessibilityId);
        element.sendKeys(key);
        logger.info("Sent " + key + " text to the element with " + accessibilityId + " accessibilityId.");
    }
}
