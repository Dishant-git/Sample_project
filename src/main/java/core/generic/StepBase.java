package core.generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

/**
 * @ScriptName    : StepBase
 * @Description   : This class contains generic functionalities like setup/tear down test environment  
 * @Author        : Digvijay Dusane(Aress)
 * @Creation Date : 05 Dec 2017   @Modified Date:                       
 */
public class StepBase 
{
	// Local Variables
	public static WebDriver driver = null;
	protected static WebDriverWait webDriverWait;
	protected static Scenario crScenario;

	/**
	 * @Method: setUp
	 * @Description:  
	 * @Author        : Digvijay Dusane(Aress)
	 * @Creation Date : 05 Dec 2017   @Modified Date: 
	 */
	public void setUp(Scenario cScenario)
	{
		crScenario = cScenario;
		try
		{
			// Load configuration file
			Properties objConfig = new Properties();
			objConfig.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/core/config.properties"));
		 	
		  
			if(objConfig.getProperty("test.browser").equalsIgnoreCase("chrome"))
			{
				// Set the capabilities for WebDriver
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				//System.setProperty("webdriver.chrome.driver", ".\\src\\main\\java\\drivers\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
				driver = new ChromeDriver(capabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
				driver.get(objConfig.getProperty("test.browser.url"));
}
			
			else if(objConfig.getProperty("test.browser").equalsIgnoreCase("firefox"))
			{
			// Set the capabilities for WebDriver
			//	firefoxOptions options = new firefoxOptions();
			//	options.addArguments("--start-maximized");
			//	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		    //	capabilities.firefox();
				//System.setProperty("webdriver.firefox.driver", ".\\src\\main\\java\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
		    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
				driver.get(objConfig.getProperty("test.browser.url"));
}

			else if(objConfig.getProperty("test.browser").equalsIgnoreCase("InternetExplorer"))
			{
			// Set the capabilities for WebDriver
			//	firefoxOptions options = new firefoxOptions();
			//	options.addArguments("--start-maximized");
			//	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		    //	capabilities.firefox();
				System.setProperty("webdriver.ie.driver", "/src/main/resources/drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
		    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
				driver.get(objConfig.getProperty("test.browser.url"));
}

		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * @Method: tearDown
	 * @Description:  
	 * @Author: Digvijay Dusane(Aress)
	 * @throws IOException 
	 * @Creation Date : 05 Dec 2017   @Modified Date:
	 */
	public void tearDown(Scenario scenario) 
	{
		try 
		{
			if (scenario.isFailed()) 
			{
				embedScreenshot();
			}
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * @Method: getDriver
	 * @Description:  
	 * @return :Appium Driver instance
	 * @Author: Digvijay Dusane(Aress)
	 * @Creation Date : 22 Nov 2017   @Modified Date:
	 */
	public WebDriver getDriver()
	{
		return driver;
	}

	/**
	 * Method: embedScreenshot
	 * Description: 
	 * @param 
	 * @author Digvijay Dusane(Aress)
	 * @Creation Date : 05 Dec 2017   @Modified Date:
	 */
	public static void embedScreenshot()
	{
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		crScenario.embed(screenshot, "image/png"); //stick it in the report
	}
	
	
}
