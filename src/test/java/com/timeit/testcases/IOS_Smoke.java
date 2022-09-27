package com.timeit.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.timeit.base.TestBase;
import com.timeit.listeners.ExtentManager;
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

public class IOS_Smoke extends TestBase {
    
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
        logger = Logger.getLogger(IOS_Smoke.class.getName());
    }

	@Test
	public void TC_0001() {
		logger.info("Running test case " + testCaseName);

		String Email = DataReader.getCellData(prop.getProperty("EmailColumn"), 1);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 1);

		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.verifyWelcomeScreenUi();
		welcomePage.navigateToLoginScreen();
		loginPage.verifyLoginScreenUi();
		loginPage.loginToApp(Email, Password);
		dashboardPage.naviagteToSettingTab();
		settingspage.signOutOfApp();
	}

	@Test
	public void TC_0002() {
		logger.info("Running test case " + testCaseName);

		String Email = DataReader.getCellData(prop.getProperty("EmailColumn"), 2);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 2);

		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_TimeLogPage timeLogPage = new IOS_TimeLogPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.navigateToLoginScreen();
		loginPage.loginToApp(Email, Password);
		dashboardPage.navigateToTimeLogTab();
		timeLogPage.addTimeEntry("Monday", "Consultation", "Night", "07:30");
		settingspage.signOutOfApp();
	}

	@Test
	public void TC_0003() {
		logger.info("Running test case " + testCaseName);

		String Email = DataReader.getCellData(prop.getProperty("EmailColumn"), 3);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 3);

		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_TimeLogPage timeLogPage = new IOS_TimeLogPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.navigateToLoginScreen();
		loginPage.loginToApp(Email, Password);
		dashboardPage.navigateToTimeLogTab();
		timeLogPage.editTimeEntry();
		settingspage.signOutOfApp();
	}

	@Test
	public void TC_0004() {
		logger.info("Running test case " + testCaseName);

		String Email = DataReader.getCellData(prop.getProperty("EmailColumn"), 4);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 4);

		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_TimeLogPage timeLogPage = new IOS_TimeLogPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.navigateToLoginScreen();
		loginPage.loginToApp(Email, Password);
		dashboardPage.navigateToTimeLogTab();
		timeLogPage.deleteTimeEntry();
		settingspage.signOutOfApp();
	}

	@Test
	public void TC_0005() {
		logger.info("Running test case " + testCaseName);

		String Email = DataReader.getCellData(prop.getProperty("EmailColumn"), 5);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 5);

		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_TimeLogPage timeLogPage = new IOS_TimeLogPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.navigateToLoginScreen();
		loginPage.loginToApp(Email, Password);
		dashboardPage.navigateToTimeLogTab();
		timeLogPage.addTimeEntryToWholeWeek();
		timeLogPage.submitTimeSheet();
		settingspage.signOutOfApp();
	}

	@Test
	public void TC_0006() {
		logger.info("Running test case " + testCaseName);

		String Email1 = DataReader.getCellData(prop.getProperty("EmailColumn"), 6);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 6);
		DataSetup.iOS_submitTimesheet(prop.getProperty("SetupEmail1"), prop.getProperty("Password"));

		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_ApprovalPage approvalPage = new IOS_ApprovalPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.navigateToLoginScreen();
		loginPage.loginToApp(Email1, Password);
		loginPage.chooseApproverRole();
		dashboardPage.navigateToApprovalTab();
		approvalPage.approveSubmittedTimesheet();
		settingspage.signOutOfApp();
	}

	@Test
	public void TC_0007() {
		logger.info("Running test case " + testCaseName);

		String Email = DataReader.getCellData(prop.getProperty("EmailColumn"), 7);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 7);
		DataSetup.iOS_submitTimesheet(prop.getProperty("SetupEmail2"), prop.getProperty("Password"));

		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_ApprovalPage approvalPage = new IOS_ApprovalPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.navigateToLoginScreen();
		loginPage.loginToApp(Email, Password);
		loginPage.chooseApproverRole();
		dashboardPage.navigateToApprovalTab();
		approvalPage.rejectSubmittedTimesheet();
		settingspage.signOutOfApp();
	}

	@Test
	public void TC_0008() {
		logger.info("Running test case " + testCaseName);

		String Email = DataReader.getCellData(prop.getProperty("EmailColumn"), 8);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 8);

		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_TimeLogPage timeLogPage = new IOS_TimeLogPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.navigateToLoginScreen();
		loginPage.loginToApp(Email, Password);
		loginPage.chooseTimekeeperRole();
		dashboardPage.navigateToTimeLogTab();
		timeLogPage.timekeeperAddsTimeSheetToTeamMembers("Wilson Fisk", "Helen Zate");
		settingspage.signOutOfApp();
		
		String MemberEmail = prop.getProperty("MemberEmail");
		String MemberPassword = prop.getProperty("Password");
		
		welcomePage.navigateToLoginScreen();
		loginPage.loginToApp(MemberEmail, MemberPassword);
		dashboardPage.navigateToTimeLogTab();
		timeLogPage.submitTimeSheet();
		settingspage.signOutOfApp();
	}
	
	
	@Test
	public void TC_0009() {
		logger.info("Running test case " + testCaseName);

		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_SignUpPage signupPage = new IOS_SignUpPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.navigateToSignUpScreen();
		signupPage.createAccount();
		signupPage.inviteMembers();
		signupPage.addApprover();
		signupPage.addTimekeeper();
		signupPage.createProject();
		signupPage.createTask();
		signupPage.selectApprover();
		signupPage.navigateToDashboard();
		dashboardPage.naviagteToSettingTab();
		settingspage.signOutOfApp();
	}

	
	@Test
	public void TC_0010() {
		logger.info("Running test case " + testCaseName);

		String Email = DataReader.getCellData(prop.getProperty("EmailColumn"), 10);
		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), 10);
		
		DataSetup.iOS_SignupAndInviteMember();
		
		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_SignUpPage signupPage = new IOS_SignUpPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		welcomePage.navigateToLoginScreen();
		signupPage.invitedMemberSignUp(Email, Password);
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
