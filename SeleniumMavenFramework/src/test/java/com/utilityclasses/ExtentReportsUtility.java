package com.utilityclasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsUtility {

	public static ExtentReports reports;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest testlogger;
	//singleton class
	private static ExtentReportsUtility extentObject=null;
	
	
	private ExtentReportsUtility()
	{
		
	}
	public static ExtentReportsUtility getInstance() {
		if (extentObject==null) {
			extentObject= new ExtentReportsUtility();
		}
		return extentObject;
	}
	
	//
	
	public void startExtentReport() {
		sparkReporter = new ExtentSparkReporter(Constants.SPARKS_HTML_REPORTS);
		reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
		
		
		reports.setSystemInfo("host name", "salesforce");
		reports.setSystemInfo("environment", "automation testing");
		reports.setSystemInfo("user name", "yamuna jansi");
		
		sparkReporter.config().setDocumentTitle("test execution report");
		sparkReporter.config().setReportName("salesforce regression test");
		sparkReporter.config().setTheme(Theme.DARK);  
	}
	public void singleTestReport(String testscript_Name) {
		testlogger=reports.createTest(testscript_Name);
	}
	public void logTestInfo(String text) {
		testlogger.info(text);
	}
	public void logTestPassed(String testCase_Name) {
		testlogger.pass(MarkupHelper.createLabel(testCase_Name +"is passed",ExtentColor.GREEN));
	}
	public void logTestFailed(String testCase_Name) {
		testlogger.fail(MarkupHelper.createLabel(testCase_Name+"is failed", ExtentColor.RED)); 
	}
	public void logTestFailedWithException(Exception e) {
		testlogger.log(Status.FAIL, e);
		
	}
	public void logTestScreenshot(String path) {
		testlogger.addScreenCaptureFromPath(path);
	}
	public void endReort() {
		reports.flush();
	}
}
