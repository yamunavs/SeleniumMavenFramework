package com.utilityclasses;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;

public class TestEventListenersUtility implements ITestListener {
	protected static ExtentReportsUtility ExtentReport;
	protected WebDriver driver;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		ExtentReport.singleTestReport(result.getMethod().getMethodName()); 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.logTestPassed(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.logTestFailed(result.getMethod().getMethodName());
		BaseTest ob= new BaseTest();
		String path=ob.getScreenShotOfThePage(driver);
		ExtentReport.logTestScreenshot(path);
	}

	

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentReport= ExtentReportsUtility.getInstance();
		ExtentReport.startExtentReport();
		System.out.println("onstartlisterners");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentReport.endReort();
	}

}
