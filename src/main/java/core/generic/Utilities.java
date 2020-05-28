package core.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ScriptName    : Utilities
 * @Description   : This class contains   
 * @Author        : Digvijay Dusane(Aress)
 * @Creation Date : 11 Dec 2017   @Modified Date:                       
 */
public class Utilities 
{
	private StepBase objStepBase = new StepBase();

	/**
	 * Method: takeScreenshot
	 * Description: 
	 * @param timeInMilliseconds
	 * @Author        : Digvijay Dusane(Aress)
	 * @Creation Date : 11 Dec 2017   @Modified Date:  
	 */
	public void takeScreenshot() 
	{
		try 
		{
			File scrFile = ((TakesScreenshot)objStepBase.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/src/test/java/com/testresult/ScreenShots/screenshot_"+ getRequiredDate(0, "yyyy_MM_dd_hh:mm:ss", null) +".png"));
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method: waitFor
	 * Description: Waits for the specified amount of [timeInMilliseconds].
	 * @param timeInMilliseconds
	 * @Author        : Digvijay Dusane(Aress)
	 * @Creation Date : 11 Dec 2017   @Modified Date:  
	 */
	public void waitFor(final Long timeInMilliseconds)
	{
		try 
		{
			Thread.sleep(timeInMilliseconds);
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method: getRequiredDate
	 * Description: This method will give require date
	 * @param incrfementDateByDays Number by which user want increase date 
	 * @param sExpectedDateFormat - User expected date format
	 * 		eg. 9 april 2014 --- dd/MM/yyyy -> 09/04/2015, dd-MM-yyyy -> 09-04-2015
	 * @param timeZoneId - Time Zone
	 * @Author        : Digvijay Dusane(Aress)
	 * @Creation Date : 11 Dec 2017   @Modified Date:  
	 */
	public String getRequiredDate (int incrementDays, String expectedDateFormat, String timeZoneId)
	{
		try 
		{
			DateFormat dateFormat;
			Calendar calendar = Calendar.getInstance();
			dateFormat = new SimpleDateFormat(expectedDateFormat);
			if(timeZoneId != null && ! timeZoneId.equals(""))
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			calendar.add(Calendar.DAY_OF_MONTH, incrementDays);
			Date tomorrow = calendar.getTime();
			String formattedDate = dateFormat.format(tomorrow);
			return formattedDate;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method: copyFileUsingStream
	 * Description:
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 12 Dec 2017   @Modified Date:
	 */
	public void copyFileUsingStream(File source, File dest) throws IOException
	{
		InputStream is = null;
		OutputStream os = null;
		try 
		{
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();

		} 
		finally 
		{
			is.close();
			os.close();
		}
	}

	/**
	 * Method: waitForPageLoad
	 * Description: timeInSeconds for the specified amount of [timeInSeconds].
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 12 Dec 2017   @Modified Date:
	 */
	public void waitForPageLoad()
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(objStepBase.getDriver(), 180);
			final JavascriptExecutor javascript = (JavascriptExecutor) 
					(objStepBase.getDriver() instanceof JavascriptExecutor ? objStepBase.getDriver() : null);
			/*
	      wait.until(new ExpectedCondition<Boolean>()
	          {
	        @Override
	        public Boolean apply(WebDriver d)
	        {
	          boolean outcome = Boolean.parseBoolean(javascript
	              .executeScript("return jQuery.active == 0")
	              .toString());
	          return outcome;
	        }
	          });

			 */   wait.until(new ExpectedCondition<Boolean>() 
					 {
				 @Override
				 public Boolean apply(WebDriver d)
				 {
					 return javascript
							 .executeScript("return document.readyState")
							 .equals("complete");
				 }
					 });
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method: waitForAjaxCallComplete
	 * Description: timeInSeconds for the specified amount of [timeInSeconds].
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 13 Dec 2017   @Modified Date:
	 */
	public void waitForAjaxCallComplete()
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(objStepBase.getDriver(), 180);
			final JavascriptExecutor javascript = (JavascriptExecutor) 
					(objStepBase.getDriver() instanceof JavascriptExecutor ? objStepBase.getDriver() : null);

			wait.until(new ExpectedCondition<Boolean>()
					{
				@Override
				public Boolean apply(WebDriver d)
				{
					boolean outcome = Boolean.parseBoolean(javascript
							.executeScript("return jQuery.active == 0")
							.toString());
					return outcome;
				}
					});
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void ConnectToDB(String ipaddress, String username, String password, String databaseName, String portnumber, String SQLquery){
//		public static void ConnectToDB(){
//		WebDriver webElement = objStepBase.getDriver();
		ResultSet resultSet = null;

		String connectionUrl = "jdbc:sqlserver://"+ipaddress+":"+portnumber+";databaseName="+databaseName+";user="+username+";password="+password+";";
//		String connectionUrl = "jdbc:sqlserver://203.193.165.235:1433;databaseName=PMTool_QA;user=sa;password=ge!f*tku12";
		try{
			Connection con = DriverManager.getConnection(connectionUrl); 
			Statement stmt = con.createStatement();
			

			String SQL = SQLquery;
			ResultSet rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
			}
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
