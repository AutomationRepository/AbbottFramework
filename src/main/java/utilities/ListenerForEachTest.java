/**
 *
 * @author Neha
 *
 */

package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import abbottLibreViewSetup.DriverFactory;
import abbottLibreViewSetup.ExtendFactory;
import abbottLibreViewSetup.ExtendReport;
import abbottLibreViewSetup.MyLogger;

public class ListenerForEachTest implements ITestListener {

	static ExtentReports report;
	ExtentTest test;
	static String val;

	@Override
	public void onTestStart(ITestResult result) {
		// test = report.createTest(result.getMethod().getMethodName());
		try {
			val = PropertiesOperations.getPropertyValueByKey("country");
		} catch (Exception e) {

		}
		String splitVal = result.getMethod().getDescription();
		Pattern pattern = Pattern.compile("([^0-9.].*?[:$])+");
		Matcher matcher = pattern.matcher(splitVal);
		if (matcher.find()) {

			if (val.equals("France (FR)")) {
				MyLogger.info("inside the france match" + matcher.group(1));
				test = report.createTest(matcher.group(1));
				ExtendFactory.setExtent(test);
			} else {
				test = report.createTest(matcher.group(0) + matcher.group(1));
				MyLogger.info("inside the france match" + matcher.group(1));
				ExtendFactory.setExtent(test);
			}
		}

		test = report.createTest(result.getMethod().getDescription());

		ExtendFactory.setExtent(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtendFactory.getExtent().log(Status.PASS, "Test Case:" + result.getMethod().getMethodName() + " is Passed.");
		ExtendFactory.removeExtentObj();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtendFactory.getExtent().log(Status.FAIL, "Test Case:" + result.getMethod().getMethodName() + " is Failed");
		ExtendFactory.getExtent().log(Status.FAIL, result.getThrowable());

		File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);

		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + actualDate + ".jpg";
		File dest = new File(screenshotPath);

		try {
			FileUtils.copyFile(src, dest);
			ExtendFactory.getExtent().addScreenCaptureFromPath(screenshotPath, "Test Case failure screenshot");
			ExtendFactory.removeExtentObj();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		try {
			if (PropertiesOperations.getPropertyValueByKey("country").equals("France (FR)")) {
				ExtendFactory.getExtent().log(Status.SKIP, "Test Case:" + result.getMethod().getMethodName()
						+ " is Skipped as it's not applicable for Data set France:French");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtendFactory.getExtent().log(Status.SKIP, "Test Case:" + result.getMethod().getMethodName() + " is Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		try {
			report = ExtendReport.setupExtendReport();
			MyLogger.info("extend report" + report.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
