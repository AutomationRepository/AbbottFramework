
/**
 * @author Neha
 *
 */

package abbottLibreViewSetup;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utilities.PropertiesOperations;

public class ExtendReport {
	static ExtentReports extent;

	// Set up the extend report and the loaction where the report must be generated.
	public static ExtentReports setupExtendReport() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		String reportPath = System.getProperty("user.dir")+"\\Reports\\ExtendReport_"+actualDate+".html";

		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(sparkReport);


		extent.setSystemInfo("Executed on Evironment: ", "url");
		extent.setSystemInfo("Executed on Evironment: ", PropertiesOperations.getPropertyValueByKey("url"));
		extent.setSystemInfo("Executed on Browser: ", PropertiesOperations.getPropertyValueByKey("browser"));
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		//extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
		extent.setSystemInfo("Executed by User: ", "Neha");
		
		sparkReport.config().setDocumentTitle("Abbott Test Framework");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("Abbott - Code Challenge");

		return extent;


	}

}
