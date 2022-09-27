package com.timeit.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.timeit.base.TestBase;
import com.timeit.util.ReusableFunctions;
import java.io.IOException;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class ExtentManager extends TestBase {

    private static ExtentReports extent;
    private static ExtentTest logger;

    public ExtentManager(ExtentReports reports){
        extent = reports;
    }

    /**
     * This method is used to get the extent reporter for framework reporting
     * @return - Instance of ExtentReport Class
     * @throws IOException - In case of unavailability of extent directory and file
     */
    public synchronized static ExtentReports getReporter() throws IOException {
        if(extent == null){
            String workingDir = System.getProperty("user.dir");
            String fileDir = workingDir+"/extentReports";
            String fileName ="ExtentReportResults"+System.getProperty("current.date")+".html";
            String reportFilePath = ReusableFunctions.createOrRetrieveFiles(fileDir, fileName);
            
            ExtentSparkReporter spark = new ExtentSparkReporter(reportFilePath);
            spark.config().setReportName("TimeIt Automation Report");
            spark.config().setDocumentTitle("TimeIt Automation Report");
            spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
            spark.config().setTheme(Theme.STANDARD);
            
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Mobile Device", prop.getProperty("iOSDeviceName"));
            extent.setSystemInfo("OS Name", prop.getProperty("Platform"));
            extent.setSystemInfo("OS Version", prop.getProperty("iOSPlatformVersion"));
            extent.setSystemInfo("Appium Server", prop.getProperty("AppiumServer"));
            extent.setSystemInfo("Team Name", prop.getProperty("TeamName"));

        }
        return extent;
    }

    /**
     * This method is used to get the extent logger for specific test case
     * @param testCaseName - currently executing test case name
     * @return - it returns instance of ExtentTest Class
     */
    public synchronized static ExtentTest getLogger(String testCaseName){
        try{
            logger = getReporter().createTest(testCaseName);
        }catch (Exception Ex){
            Ex.printStackTrace();
        }
        return logger;
    }
}

