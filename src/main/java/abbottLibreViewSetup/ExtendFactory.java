
/**
*  @author Neha
*
*/

package abbottLibreViewSetup;

import com.aventstack.extentreports.ExtentTest;

public class ExtendFactory {


		static ThreadLocal<ExtentTest> extent = new ThreadLocal<>();

		//get the current thread copy of the extentTest
		public static ExtentTest getExtent() {
			return extent.get();
		}

		//set the current extentTest with threadlocal object to achieve synchronization
		public static void setExtent(ExtentTest extentParam) {
			extent.set(extentParam);
		}

		public static void removeExtentObj() {
			extent.remove();
		}

	}

