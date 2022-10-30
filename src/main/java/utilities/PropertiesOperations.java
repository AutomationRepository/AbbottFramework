/**
 *
 * @author Neha
 *
 */

package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesOperations {

	static Properties prop = new Properties();

	// read the value from the properties file placed under the test resource
	// folder.
	public static String getPropertyValueByKey(String key) throws Exception {

		String propFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
		FileInputStream fis = new FileInputStream(propFilePath);
		prop.load(fis);

		String value = prop.get(key).toString();

		if (value == null || value.isEmpty()) {
			throw new Exception("Value is not specified for key:" + key + "in properties file");
		}

		return value;
	}

	public static void setPropertyValueByKey(String key, String value) {
		prop.setProperty(key, value);
	}

}
