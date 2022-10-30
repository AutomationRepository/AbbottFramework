
/**
 * @author Neha
 *
 */
package abbottLibreViewSetup;

import org.openqa.selenium.WebDriver;


public class DriverFactory {

	static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	//get the current thread copy of the driver
	public static WebDriver getDriver() {
		return driver.get();
	}

	//set the current driver with threadlocal object to achieve synchronization
	public static void setDriver(WebDriver driverParam) {
		driver.set(driverParam);
	}


	//Closing the browser of the current thread.
	public static void closeBrowser() {
		driver.get().close();
		driver.remove();
	}
}
