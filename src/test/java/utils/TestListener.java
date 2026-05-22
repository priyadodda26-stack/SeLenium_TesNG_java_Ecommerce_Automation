package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testcases.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getReport()
                .createTest(result.getMethod().getMethodName());

        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();

        String path = ScreenShot.capture(driver, result.getName());

        if (path != null) {
            ExtentManager.getTest().pass(
                    "Test Passed",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
        } else {
            ExtentManager.getTest().pass("Test Passed (No screenshot)");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = BaseTest.getDriver();

        ExtentManager.getTest().fail(result.getThrowable());

        String path = ScreenShot.capture(driver, result.getName());

        if (path != null) {
            ExtentManager.getTest().fail(
                    "Test Failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );}
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().skip("Test Skipped");
        }


    }
    @Override
    public void onFinish(ITestContext context) {

        ExtentManager.getReport().flush();
    }
}