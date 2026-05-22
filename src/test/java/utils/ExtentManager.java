package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager  {
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest>  tl = new ThreadLocal<>();

    public static ExtentReports getReport(){
        if(extent==null) {

            String Path = System.getProperty("user.dir" ) + "/resources/ExtentReport.html";
            System.out.println((Path));
            sparkReporter  = new ExtentSparkReporter(Path);
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Selenium Framework Report");
            sparkReporter.config().setTheme(Theme.STANDARD);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("User", "QA Engineer");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static void setTest(ExtentTest extentTest) {
        tl.set(extentTest);
    }

    public static ExtentTest getTest() {
        return tl.get();
    }
}
