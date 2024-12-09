package test_website;

import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertMessagePrint extends DriverSetup {
	public static WebElement confirmation;
	public static WebElement warning;
	public static String confirmation_text_xpath = "//div[@class='alert alert-success alert-dismissible']";
	public static String warning_xpath = "//div[@class='alert alert-danger alert-dismissible']";

	public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;  // Alert is present
        } catch (Exception e) {
            return false;  // No alert found
        }
    }
	
	public void alert() 
	{
		try {
			//For green box or success message
			confirmation = driver.findElement(By.xpath(confirmation_text_xpath)); // confirmation_text_path is initialized in DriverSetup
			if(isElementPresent() == true) {
				String confirmation_text = confirmation.getText();
				System.out.println(confirmation_text);
			}
		} catch (Exception e) {
			// TODO: handle exception
			warning();
		}
	}
	
	public void warning() {
		try {
			//For red box or warnings
			warning = driver.findElement(By.xpath(warning_xpath));
			if(isElementPresent() == true) {
				String warning_text = warning.getText();
				System.out.println(warning_text);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Absence of alert.");
		}
		
	}
	public boolean isElementPresent(){
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmation_text_xpath)));
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(warning_xpath)));
				return true;
			} catch (NoSuchElementException e2) {
				// TODO: handle exception
				return false;
			}
		}
	
	}
}
