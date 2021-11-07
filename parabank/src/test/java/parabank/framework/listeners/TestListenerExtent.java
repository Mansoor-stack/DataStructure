package parabank.framework.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

import lombok.extern.slf4j.Slf4j;
import parabank.framework.reports.ExtentManager;
import parabank.framework.reports.ExtentTestManager;

@Slf4j
public class TestListenerExtent implements ITestListener {
	ExtentReports extent;
	WebDriver driver;

	@Override
	public void onStart(ITestContext context) {
		extent = ExtentManager.getReporter();
		System.out.println("Test started");
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().pass("Test passed");
		log.debug(result.getMethod().getMethodName() + " - Test Passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().fail(result.getThrowable());
		log.error(result.getMethod().getMethodName() + " - Test Failed.");
		log.error(result.getThrowable().toString());
		driver = (WebDriver) result.getTestContext().getAttribute("driver");
		takeScreenshot(driver, result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().skip("Test skipped");
		log.debug(result.getMethod().getMethodName() + " - Test Skipped.");
	}

	public static void takeScreenshot(WebDriver driver, ITestResult result) {
		String root = System.getProperty("user.dir");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File ss = ts.getScreenshotAs(OutputType.FILE);
		long random = System.currentTimeMillis();
		String des = root + "//target//screenshots//ss" + result.getMethod().getMethodName() + random + ".png";
		try {
			FileUtils.copyFile(ss, new File(des));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExtentTestManager.getTest().addScreenCaptureFromPath(des);
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("on Finish");
		ExtentManager.printResults();
	}

}
