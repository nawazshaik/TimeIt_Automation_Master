package com.timeit.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.timeit.base.TestBase;
import com.timeit.listeners.ExtentManager;
import com.timeit.pages.Android_DashboardPage;
import com.timeit.pages.Android_LoginPage;
import com.timeit.pages.Android_SettingsPage;
import com.timeit.pages.Android_WelcomePage;
import com.timeit.pages.IOS_ApprovalPage;
import com.timeit.pages.IOS_DashboardPage;
import com.timeit.pages.IOS_LoginPage;
import com.timeit.pages.IOS_SettingsPage;
import com.timeit.pages.IOS_SignUpPage;
import com.timeit.pages.IOS_TimeLogPage;
import com.timeit.pages.IOS_WelcomePage;
import com.timeit.testdata.DataReader;
import com.timeit.testdata.DataSetup;
import com.timeit.util.ReusableFunctions;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;

public class Android_Smoke extends TestBase {
    
    private static String testCaseName = null;
    private static ExtentTest extentLogger = null;
    private static ExtentReports extent = null;
    private static Logger logger = null;

    /**
     * @author NawazShaik
     * This method is used to launch the app and to initiate the driver
     * @throws IOException - In case of unavailability of directory and config file
     */
    @BeforeMethod
    public void setUp(Method method) throws IOException {
        driverInitialization();
        driver.resetApp();
        testCaseName = method.getName();
        extent = ExtentManager.getReporter();
        extentLogger = ExtentManager.getLogger(testCaseName);
        logger = Logger.getLogger(Android_Smoke.class.getName());
    }

	@Test
	public void TC_0001() {
		logger.info("Running test case " + testCaseName);

		String Email = DataReader.getCellData(prop.getProperty("EmailColumn"), 1);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 1);

		Android_WelcomePage welcomePage = new Android_WelcomePage();
		Android_LoginPage loginPage = new Android_LoginPage();
		Android_DashboardPage dashboardPage = new Android_DashboardPage();
		Android_SettingsPage settingspage = new Android_SettingsPage();

		welcomePage.verifyWelcomeScreenUi();
		welcomePage.navigateToLoginScreen();
		loginPage.verifyLoginScreenUi();
		loginPage.loginToApp(Email, Password);
		dashboardPage.naviagteToSettingTab();
		settingspage.signOutOfApp();
	}

	
	
	/**
     * This method is used to log the test results to extent reports after every test method
     * @throws Exception - In case of any runtime exceptions
     **/
	@AfterMethod
	public void getResult(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = ReusableFunctions.takeScreenShot("TimeItApp_FailScreenshot_");
			extentLogger.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
			extentLogger.fail(result.getThrowable());
			extentLogger.fail("Check the screenshot of failed screen above: " + extentLogger.addScreenCaptureFromPath(screenShotPath, testCaseName));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentLogger.log(Status.SKIP, MarkupHelper
					.createLabel(result.getName() + " Test case SKIPPED due to below issues:", ExtentColor.GREY));
			extentLogger.skip(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentLogger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test case PASSED.", ExtentColor.GREEN));

		}

		if (driver != null) {
			driver.resetApp();
		}
	}

	/**
     * This method is used to generate extent reports at the end of test executions
     **/
	@AfterTest
	public void tearDown() {
		extent.flush();
	}
}
