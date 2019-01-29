package publicTransportation.publicTransportation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class BaseTest {
WebDriver driver;
	

	public void setUpBrowser()
	{
		String os = System.getProperty("os.name").toLowerCase();
		
		if(os.contains("mac"))
	
		{
		System.setProperty("webdriver.chrome.driver", "/Users/gurjeet/eclipse-workspace/publicTransportation/chromedriver");
	    driver = new ChromeDriver();  // system pro
		}
	    else
	    {
	    	System.setProperty("webdriver.chrome.driver", "/Users/gurjeet/eclipse-workspace/publicTransportation/BrowserDrivers/chromedriver.exe");
		    driver = new ChromeDriver(); 
	    }
		driver.get("http://fmsview.mtc.ca.gov");
	}
}
