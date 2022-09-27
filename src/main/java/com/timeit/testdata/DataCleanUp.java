package com.timeit.testdata;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.timeit.base.TestBase;
import com.timeit.pages.IOS_DashboardPage;
import com.timeit.pages.IOS_LoginPage;
import com.timeit.pages.IOS_SettingsPage;
import com.timeit.pages.IOS_TimeLogPage;
import com.timeit.pages.IOS_WelcomePage;


public class DataCleanUp extends TestBase{

	private static String testCaseName = null;
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
        logger = Logger.getLogger(DataCleanUp.class.getName());
    }
    
   
    @Test
    public static void iOS_timesheetCleanUp() {
    	for(int i=1; i<=4; i++) {
    		String Email = DataReader.getCellData("Data CleanUp", i);
    		String Password = DataReader.getCellData(prop.getProperty("PasswordColumn"), i);
    		logger.info("#### Data Cleanup started for user: "+Email);
    		
    		IOS_WelcomePage welcomePage = new IOS_WelcomePage();
    		IOS_LoginPage loginPage = new IOS_LoginPage();
    		IOS_DashboardPage dashboardPage = new IOS_DashboardPage();
    		IOS_TimeLogPage timeLogPage = new IOS_TimeLogPage();
    		IOS_SettingsPage settingspage = new IOS_SettingsPage();

    		welcomePage.navigateToLoginScreen();
    		loginPage.loginToApp(Email, Password);
    		dashboardPage.navigateToTimeLogTab();
    		timeLogPage.deleteAllEntries();
    		settingspage.signOutOfApp();
    		logger.info("#### Data Cleanup completed for user: "+Email);
    	}
    	
    }

    
 }
