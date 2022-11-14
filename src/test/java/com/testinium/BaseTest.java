package com.testinium;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class BaseTest {
    protected static AppiumDriver<MobileElement> appiumDriver;
    protected static Logger logger = LogManager.getLogger(BaseTest.class);
    protected boolean localAndroid = true;
    protected String deviceName = "b81ade34";
    protected String appActivity = "com.m.qr.home.main.ui.HomeActivity";
    protected String appPackage = "com.m.qr";
    protected String urlString = "http://localhost:4723/wd/hub";

    @BeforeScenario
    public void initialise() throws MalformedURLException {
        if(localAndroid) {
            logger.info("Android test begins.");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities() {{
                setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
                setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
                setCapability("autoAcceptAlerts", true);
                setCapability("autoDismissAlerts", true);
            }};
            URL url = new URL(urlString);
            appiumDriver = new AppiumDriver<MobileElement>(url, desiredCapabilities);
        } else {
            
            logger.info("IOS test begins.");
        }
        logger.info("Connection initialised");
    }

    @AfterScenario
    public void terminate() {
        logger.info("Connection terminated.");
        appiumDriver.quit();
    }
}
