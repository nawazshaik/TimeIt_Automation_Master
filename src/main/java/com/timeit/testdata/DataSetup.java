package com.timeit.testdata;

import org.apache.log4j.Logger;

import com.timeit.base.TestBase;
import com.timeit.pages.IOS_DashboardPage;
import com.timeit.pages.IOS_LoginPage;
import com.timeit.pages.IOS_SettingsPage;
import com.timeit.pages.IOS_SignUpPage;
import com.timeit.pages.IOS_TimeLogPage;
import com.timeit.pages.IOS_WelcomePage;

public class DataSetup extends TestBase{
	
	private static Logger logger = Logger.getLogger(DataSetup.class.getName());
	
	public static void iOS_submitTimesheet(String Email, String Password) {
		
		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_LoginPage loginPage = new IOS_LoginPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_TimeLogPage timeLogPage = new IOS_TimeLogPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		logger.info("*** Started Data Setup ***");
		welcomePage.navigateToLoginScreen();
		loginPage.loginToApp(Email, Password);
		dashboardPage.navigateToTimeLogTab();
		timeLogPage.addTimeEntryToWholeWeek();
		timeLogPage.submitTimeSheet();
		settingspage.signOutOfApp();
		logger.info("*** Data Setup Successful ***");
	}

	
	public static void iOS_SignupAndInviteMember() {
		
		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
		IOS_SignUpPage signupPage = new IOS_SignUpPage();
		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
		IOS_SettingsPage settingspage = new IOS_SettingsPage();

		logger.info("*** Started Data Setup ***");
		welcomePage.navigateToSignUpScreen();
		signupPage.createAccount();
		signupPage.inviteMembers();
		signupPage.addApprover();
		signupPage.createProject();
		signupPage.createTask();
		signupPage.selectApprover();
		signupPage.navigateToDashboard();
		dashboardPage.naviagteToSettingTab();
		settingspage.signOutOfApp();
		logger.info("*** Data Setup Successful ***");
	}
	
}
