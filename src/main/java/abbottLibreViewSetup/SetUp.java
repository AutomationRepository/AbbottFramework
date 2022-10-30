/**
 *
 * @author Neha
 *
 */

package abbottLibreViewSetup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.PropertiesOperations;

public class SetUp {

	BrowserFactory bf = new BrowserFactory();

	@BeforeMethod
	public void launch() throws Exception {

		DriverFactory.setDriver(bf.createBrowserInstance(PropertiesOperations.getPropertyValueByKey("browser")));
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(PropertiesOperations.getPropertyValueByKey("url"));
	}

	@AfterMethod
	public void tearDown() {
		try {
			DriverFactory.closeBrowser();
		}
		catch(Exception e) {
			MyLogger.info("The browser is closed already");
		}
		
	}

}
