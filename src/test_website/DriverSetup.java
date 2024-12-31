package test_website;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverInfo;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup 
{
	public static WebDriver driver;
	
	// Set up Chrome options 
	ChromeOptions options; 
					
	public static String url = "https://tutorialsninja.com/demo/index.php?route=common/home";
	//public static String url = "https://tutorialsninja.com/demo/index.php?route=account/register"; //Register
	//public static String url = "https://tutorialsninja.com/demo/index.php?route=common/home"; // Add to cart
	
	WebDriverWait wait;
		
	@BeforeSuite(alwaysRun = true)
	public void openBrowser() {
		options =new ChromeOptions();
		options.addArguments("--disable-bookmarks-bar"); // Disable bookmarks bar
		options.addArguments("--disable-notifications");  // Disable notifications
		options.addArguments("--disable-infobars");  //Disable infobars that might pop up
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@AfterSuite(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(15000);
		if (driver != null) {
			driver.quit();
		}
	}

}
