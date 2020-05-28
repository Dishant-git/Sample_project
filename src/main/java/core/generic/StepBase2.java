package core.generic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

/**
 * @ScriptName : StepBase
 * @Description : This class contains generic functionalities like setup/tear
 *              down test environment
 * @Author : Digvijay Dusane(Aress)
 * @Creation Date : 07 Dec 2017 @Modified Date:
 */
public class StepBase2 {
	// Local Variables
	protected static AppiumDriver appiumDriver = null;
	protected static WebDriverWait webDriverWait;
	protected static Scenario crScenario;

	/**
	 * @Method: setUp
	 * @Description:
	 * @Author : Digvijay Dusane(Aress)
	 * @Creation Date : 05 Dec 2017 @Modified Date:
	 */
	public void setUp(Scenario cScenario) {
		crScenario = cScenario;
		try {
			// Load configuration file
			Properties objConfig = new Properties();
			objConfig.load(
					new FileInputStream(System.getProperty("user.dir") + "/src/main/java/core/config.properties"));

			System.out.println("Config file loaded");

			File application = new File(objConfig.getProperty("file_path"));
			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
			objConfig.getProperty("device.platformName"));
			capabilities.setCapability(MobileCapabilityType.VERSION, objConfig.getProperty("device.platformVersion"));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, objConfig.getProperty("device.name"));
			capabilities.setCapability("app", application.getAbsolutePath());

			// driver = (AppiumDriver) new RemoteWebDriver(new
			// URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			appiumDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

			appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * @Method: tearDown
	 * @Description:
	 * @Author: Digvijay Dusane(Aress)
	 * @throws IOException
	 * @Creation Date : 05 Dec 2017 @Modified Date:
	 */
	// public void tearDown(Scenario scenario)
	// {
	// try
	// {
	// if (scenario.isFailed())
	// {
	// embedScreenshot();
	// }
	// //appiumDriver.manage().deleteAllCookies();
	// appiumDriver.quit();
	// }
	// catch(Exception exception)
	// {
	// exception.printStackTrace();
	// }
	// }

	/**
	 * @Method: getDriver
	 * @Description:
	 * @return :Appium Driver instance
	 * @Author: Digvijay Dusane(Aress)
	 * @Creation Date : 05 Dec 2017 @Modified Date:
	 */
	public AppiumDriver getDriver() {
		return appiumDriver;
	}

	/**
	 * Method: embedScreenshot Description:
	 * 
	 * @param
	 * @author Digvijay Dusane(Aress)
	 * @Creation Date : 05 Dec 2017 @Modified Date:
	 */
	public static void embedScreenshot() {
		final byte[] screenshot = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);
		crScenario.embed(screenshot, "image/png"); // stick it in the report
	}
}
