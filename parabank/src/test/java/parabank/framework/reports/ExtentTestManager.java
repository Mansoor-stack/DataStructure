package parabank.framework.reports;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	
	public static ExtentTest test;
	public static HashMap<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	
	@SuppressWarnings("boxing")
	public static synchronized void startTest(String testName) {
		test = ExtentManager.extentReports.createTest(testName);
		extentTestMap.put((int) (Thread.currentThread().getId()), test);
	}

	@SuppressWarnings("boxing")
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (Thread.currentThread().getId()));
	}
}
