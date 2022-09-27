package com.timeit.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestBase {

    public static Properties prop;
    public static AppiumDriver<MobileElement> driver;
    private static Logger logger;

    /**
     * @author NawazShaik
     * This static block is used to format the date 
     **/
    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hhmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }
    

    /**
     * This method is used to load config file and log4j file 
     * @throws MalformedURLException - In case of invalid appium server url
     **/
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/timeit/config/config.properties");
            prop.load(inputStream);

            PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resource/log4j.properties");
            logger = Logger.getLogger(MethodHandles.lookup().lookupClass());

        } catch (FileNotFoundException Ex) {
            logger.info("File not found: " + Ex.getMessage());

        } catch (IOException Ex) {
            logger.info("Exception occurred: " + Ex.getMessage());
        }
    }

    /**
     * This is singleton driver initialization method
     * @throws MalformedURLException - In case of invalid appium server url
     */
    public static void driverInitialization() throws MalformedURLException{
        if (driver == null) {
            switch (prop.getProperty("Platform")){
                case "android":
                    logger.info("Running Tests On Android Platform.");
                    androidSetup();
                    break;
                case "ios":
                    logger.info("Running Tests On IOS Platform.");
                    iosSetup();
                    break;
                default:
                    logger.info("Running Tests On Browser Stack.");
                    iosSetup();
            }
        }
    }

    /**
     * This method is used for android driver setup
     * @throws MalformedURLException - In case of invalid appium server url
     */
    private static void androidSetup() throws MalformedURLException {
    	DesiredCapabilities caps = new DesiredCapabilities();
    	caps.setCapability("automationName", prop.getProperty("AndroidAutomationName"));
        caps.setCapability("deviceName", prop.getProperty("AndroidDeviceName"));
        caps.setCapability("udid", prop.getProperty("AndroidDeviceID"));
        caps.setCapability("platformName", prop.getProperty("AndroidPlatformName"));
        caps.setCapability("platformVersion", prop.getProperty("AndroidPlatformVersion"));
        caps.setCapability("appPackage", prop.getProperty("AppPackage"));
        caps.setCapability("appActivity", prop.getProperty("AppActivity"));
        caps.setCapability("app", System.getProperty("user.dir") + "/apps/" + prop.getProperty("ApkName"));
        caps.setCapability("noReset", prop.getProperty("NoReset"));
        caps.setCapability("autoGrantPermissions", prop.getProperty("AutoGrantPermissions"));
        driver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("AppiumServer")), caps);
        logger.info("Starting Android Driver.");
    }


    /**
     * This method is used for iOS driver setup
     * @throws MalformedURLException - In case of invalid appium server url
     */
    private static void iosSetup() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
    	caps.setCapability("automationName", prop.getProperty("iOSAutomationName"));
        caps.setCapability("deviceName", prop.getProperty("iOSDeviceName"));
        caps.setCapability("bundleId", prop.getProperty("BundleId"));
        caps.setCapability("platformName", prop.getProperty("Platform"));
        caps.setCapability("udid", prop.getProperty("iOSDeviceID"));
        caps.setCapability("noReset", Boolean.TRUE);
        caps.setCapability("platformVersion", prop.getProperty("iOSPlatformVersion"));
        caps.setCapability("app", System.getProperty("user.dir") + "/apps/"+ prop.getProperty("iOSAppName"));
        driver = new IOSDriver<MobileElement>(new URL(prop.getProperty("AppiumServer")), caps);
        logger.info("Starting IOS Driver.");
    }

}