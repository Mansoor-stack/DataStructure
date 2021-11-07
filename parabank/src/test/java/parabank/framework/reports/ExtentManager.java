package parabank.framework.reports;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.AbstractReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import parabank.framework.constants.UIConstants;

public class ExtentManager {
	public static ExtentReports extentReports;
	public static ExtentSparkReporter sparkReporter;

	public synchronized static ExtentReports getReporter(){
	    SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
	    String currDate = formatter.format(new Date());
		try {
	    File srcDir = new File(UIConstants.ROOT_DIR + "\\reports\\current");
	    FileFilter fileFilter = new WildcardFileFilter("*_report.html");
	    File[] files = srcDir.listFiles(fileFilter);
	    String destDir = UIConstants.ROOT_DIR + "\\reports\\archive";
	    if(files != null) {
	    for (File f : files) {
	      System.out.println(f.getAbsolutePath());
	      FileUtils.copyFile(f, new File(destDir+ "\\" + f.getName()));
	      FileUtils.forceDelete(f);
	    }
	    }
		String reportPath = UIConstants.ROOT_DIR + "\\reports\\current\\"+currDate+"_report.html";
		sparkReporter= new ExtentSparkReporter(reportPath);

			sparkReporter.loadXMLConfig(new File(UIConstants.ROOT_DIR + "\\src\\test\\resources\\extent-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(extentReports == null) {
			extentReports = new ExtentReports();
			extentReports.attachReporter(sparkReporter);
		}
		return extentReports;
	}	
	
	public synchronized static void printResults() {
		extentReports.flush();
	}	
}
