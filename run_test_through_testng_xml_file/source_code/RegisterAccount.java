package test_website;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class RegisterAccount extends DriverSetup
{
	public static String fname = "John";
	public static String lname = "Doe";
	public static String email = "johnDoe10056@gmail.com";
	public static String tel = "9876543210";
	public static String password = "johnDoe1";
	
	//AlertMessagePrint alert = new AlertMessagePrint();
	
	@Test(priority = 2)
	public void register() throws InterruptedException 
	{
		// handleAlert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		driver.findElement(By.cssSelector("li[class='dropdown open'] li:nth-child(1) a:nth-child(1)")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='input-firstname']"))).sendKeys(fname);
		driver.findElement(By.id("input-lastname")).sendKeys(lname);
		
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys(tel);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.id("input-confirm")).sendKeys(password);
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		try {
			driver.findElement(By.cssSelector(".btn.btn-primary")).click();
			//alert.alert();
		} catch (Exception e) {
			// TODO: handle exception
			//alert.alert();
			System.out.println("Button not visibile.");
		}
		
		System.out.println("Registration successful!");
	}                                                             
}
