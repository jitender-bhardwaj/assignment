package testPackage;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import objectRepositoty.Objects;

public class TestClass implements Objects{
	public static WebDriver driver;
	public static String usrDir;
	public String amazonPrice, flipkartPrice;
	public int flipkartValue, amazonValue;

	@BeforeTest
	public void initialization() {
		usrDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", usrDir+"\\driver\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	public String productSearch(String url, String searchField, String searchIcon, String productName, String productPrice) throws InterruptedException {
		String parentWindow, productValue = null; 
		//Navigating to the url
		driver.get(url);
		Thread.sleep(3000);
		System.out.println("Launched WebApp");
		
		Boolean loginPopupPresent = driver.findElements(By.xpath(string_LoginPopUp)).size() > 0;
		
		if(loginPopupPresent)
			driver.findElement(By.xpath(string_LoginPopUp)).click();
		driver.findElement(By.xpath(searchField)).sendKeys(string_SearchText);
		driver.findElement(By.xpath(searchIcon)).click();
		if(driver.findElement(By.xpath(productName)) != null) {
			parentWindow = driver.getWindowHandle();
			driver.findElement(By.xpath(productName)).click();
			Set<String> allWindowHandles = driver.getWindowHandles();
	        Iterator<String> iterator = allWindowHandles.iterator();
	 
	        while (iterator.hasNext()) {
	            String ChildWindow = iterator.next();
	            if (!parentWindow.equalsIgnoreCase(ChildWindow)) {
	            	driver.switchTo().window(ChildWindow);
	                System.out.println("Focus has been changed to the child window");
	                productValue = driver.findElement(By.xpath(productPrice)).getText();
	            }
	        }
	        driver.close();
	        driver.switchTo().window(parentWindow);
		}
		else
		{
			System.out.println("Product you are searching for is not available on Website '" + string_AUrl.split("www.", 1) +"'");
		}
		return productValue;
	}
	
	@Test(priority = 1)
	public void amazonSearch() throws InterruptedException
	{
		String amazonPrice = productSearch(string_AUrl, string_ASearchField, string_ASearchIcon, string_AProductName, string_AProductPrice);
		System.out.println("The listing price on "+ string_AUrl.split("www.")[1] +" is: " + amazonPrice);
		amazonPrice = amazonPrice.split("\\.")[0];
		//System.out.println(amazonPrice.split("\\.")[0]);
		//System.out.println(amazonPrice.split(".00")[1]);
		amazonPrice = amazonPrice.replaceAll("[^0-9]", "");
		amazonValue = Integer.valueOf(amazonPrice);
		System.out.println("value of integer is: "+ amazonPrice);
	}
	
	@Test(priority = 2)
	public void flipkartSearch() throws InterruptedException
	{
		String flipkartPrice = productSearch(string_FUrl, string_FSearchField, string_FSearchIcon, string_FProductName, string_FProductPrice);
		System.out.println("The listing price on "+ string_FUrl.split("www.")[1] +" is: " + flipkartPrice);
		flipkartPrice = flipkartPrice.replaceAll("[^0-9]", "");
		flipkartValue = Integer.valueOf(flipkartPrice);
		System.out.println("value of integer is: "+ flipkartPrice);
	}
	
	@Test(priority = 3)
	public void comparingPrice() {
        int result =  Integer.compare(amazonValue, flipkartValue);    
        if(result > 0) {  
           System.out.println("Price on Amazon is greater than Flipkart");  
        } else if(result< 0) {  
           System.out.println("Price on Amazon is less than Flipkart");  
        } else {  
           System.out.println("Price on Amazon is equal as of Flipkart");  
        }  
	}
	
	@AfterTest
	public void disposing()
	{
		driver.quit();
	}
}
