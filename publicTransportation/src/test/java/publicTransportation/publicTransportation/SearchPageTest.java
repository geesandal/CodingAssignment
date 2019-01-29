package publicTransportation.publicTransportation;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.restassured.RestAssured;

import org.testng.Assert;
import org.testng.annotations.*;

public class SearchPageTest {
	

WebDriver driver ;
	
	@BeforeTest
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
		
		}
	
	//0. Verify you get a 200 response from the website.
	@Test(priority=1)
	public void getHTTPResponseCodeOfURL(String url) 
	{
//		driver.get("http://fmsview.mtc.ca.gov");
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
         Assert.assertEquals(RestAssured.get("http://fmsview.mtc.ca.gov").statusCode(),"200");
        
    }
	
	
	@Test(priority=2)
	public void  SearchByCity() throws InterruptedException
	{
	
driver.get("http://fmsview.mtc.ca.gov");
	Thread.sleep(3000);
	//1. Click on the text entry area on the 'Search for' label, and type Alameda.
driver.findElement(By.id("mat-input-0")).sendKeys("Alameda");
	Thread.sleep(8000);
	
	//2. Verify to make sure you can retrive more than 2 results using Selenium.
	String numberOfResultsFound= driver.findElement(By.xpath("//*[@class='label2']")).getText();

	System.out.println("number of results: "+numberOfResultsFound);
	  String expected = numberOfResultsFound.substring(0, numberOfResultsFound.length()-5).trim();	
	  System.out.println("Number of Results Found: "+ expected);
	
	  if(Integer.parseInt(expected)>2)
	  {
		  System.out.println("Number of results are greater than 2");
	  }
	
	//3. Click on East Bay Greenway,   
	driver.findElement(By.xpath("//span[contains(text(),'East Bay Greenway')]")).click();
	Thread.sleep(3000);
	
	//and make sure the browser go to the next page.
	Assert.assertEquals(driver.getCurrentUrl(), "http://fmsview.mtc.ca.gov/fms50/projects-masterdetails/TPJ158");	
	Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'ALA150008')]")).getText(), "ALA150008");
	
	//4. Verify the Jurisdiction is 'Alameda County'
	Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Alameda County')]")).getText(), "Alameda County");

	//5. Verify the status is Active
	Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Active')]")).getText().substring(7), "Active");

	//6. Verify the Investment Type is 100% Expansion.
	Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'100% Expansion')]")).getText(), "100% Expansion");
	
	
	}
	
	@Test(priority=3)
	public void  SearchByTIPID() throws InterruptedException
	{
	
	driver.get("http://fmsview.mtc.ca.gov");
	Thread.sleep(3000);
	//1. Click on the text entry area on the 'Search for' label, and type TIP ID.
	driver.findElement(By.id("mat-input-0")).sendKeys("ALA110033");
	Thread.sleep(3000);
	
	//2. Click on Alameda County Safe Routes to School,   
	driver.findElement(By.xpath("//span[contains(text(),'Alameda County Safe Routes to School')]")).click();
	Thread.sleep(3000);
	
	//3. Verify the CTIPS ID
	Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'20600004908')]")).getText().substring(6), "20600004908");

	//4. Verify the TIP ID 
	Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'ALA110033')]")).getText(), "ALA110033");
	
	
	}
	
	@Test(priority=4)
	public void  SearchByACTC() throws InterruptedException
	{
	
	driver.get("http://fmsview.mtc.ca.gov");
	Thread.sleep(3000);
	//1. Click on the text entry area on the 'Search for' label, and type TIP ID.
	driver.findElement(By.id("mat-input-0")).sendKeys("ACTC");
	Thread.sleep(3000);
	
	//2. Click on 7th Street Grade Separation East,   
	driver.findElement(By.xpath("//span[contains(text(),'7th Street Grade Separation East')]")).click();
	Thread.sleep(3000);
	
	//3. Verify the Sponsor
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='item item-1']//p[3]")).getText().contains("ACTC"));

	
	}
	@AfterTest
	public void closeBrowser()
	{
		driver.quit();
	}
	

}
