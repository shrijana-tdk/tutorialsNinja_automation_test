package test_website;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Cart extends SearchItems{
	
	@Test(priority=4)
	public void goToCart(){
		//handleAlert();
		try {
			WebElement cart =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Shopping Cart'] i[class='fa fa-shopping-cart']")));
			cart.click();
			
		}
		catch (ElementClickInterceptedException e) {
		// TODO: handle exception
			WebElement cart =  wait.until(ExpectedConditions.elementToBeClickable(By.className("btn btn-inverse btn-block btn-lg dropdown-toggle")));
			cart.click();
			
			WebElement view_cart =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[normalize-space()='View Cart']")));
			view_cart.click();
		}
		catch (InvalidSelectorException e) {
			// TODO: handle exception
			WebElement cart_txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='shopping cart']")));
			cart_txt.click();
		}
		
		updateAnItemQuantity();
		removeAnItem();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.navigate().refresh();
		fill();
		redirect_to_item_page();
	}
	
	public void updateAnItemQuantity() {
		//WebElement quantity1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value='1'][name='quantity[210363]']"))); //iphone quantity
		//WebElement quantity1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='quantity[210363]'])[1]"))); //iphone quantity'
		WebElement quantity1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[4]/div[1]/input[1]"))); //iphone quantity

		quantity1.clear();
		quantity1.sendKeys("3");
		
		//String update_cssselector = "body > div:nth-child(4) > div:nth-child(4) > div:nth-child(1) > form:nth-child(2) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4) > div:nth-child(1) > span:nth-child(2) > button:nth-child(1) > i:nth-child(1)";
		WebElement update = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/span[1]/button[1]/i[1]"));
		update.click();
		
		System.out.println("The quantity of product is updated to 3.");
	}
	
	public void removeAnItem() { 
		driver.findElement(By.xpath("(//i[@class='fa fa-times-circle'])[1]"));
		alert();
		System.out.println("An item is deleted.");
	}
	
	public void fill() {
		coupon();
		try {
			estimate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No Estimate element.");
		}
		gift();
		checkout();
	}
	public void redirect_to_item_page () {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"))).click();
		System.out.print("Redirected to item page");
	}
	public void coupon() {
		//Fill Use Coupon Code
				driver.findElement(By.xpath("//a[normalize-space()='Use Coupon Code']")).click();
				driver.findElement(By.id("input-coupon")).sendKeys("COUPON123");
				driver.findElement(By.id("button-coupon")).click();
				alert();
				System.out.println("Use Coupon Code Successful.");
	}
	public void gift() {
		//Fill Use Gift Certificate
				driver.findElement(By.xpath("//a[normalize-space()='Use Gift Certificate']")).click();
				driver.findElement(By.id("input-voucher")).sendKeys("ASDFG5678");
				driver.findElement(By.id("button-voucher")).click();
				alert();
				System.out.println("Apply Gift Certificate Successful.");
	}
	public void checkout() {
		WebElement checkout_btn = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
		wait.until(ExpectedConditions.visibilityOf(checkout_btn));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(checkout_btn)).click(); //Click Checkout button
			System.out.println("Checkout Successful.");
		} catch (ElementClickInterceptedException e) {
			// TODO: handle exception
			System.out.println("An exception occurs " +e.getMessage());
		}
		
	}
	public void estimate() {
		//Fill Estimate Shipping & Taxes
		WebElement tgl_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Estimate Shipping & Taxes']")));
		tgl_btn.click();
		//Select COuntry
		try {
			WebElement country_dropdown = driver.findElement(By.id("input-country"));
			Select country = new Select(country_dropdown);
			
			List<WebElement> options = country.getOptions();
			
			if (options == null) {
				System.out.println("List of Country is empty.");
			}
			else {
				//select.selectByContainsVisibleText("Argentina");
				country.selectByIndex(1);
				System.out.println("Country Input successful.");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("An error occurred: " + e.getMessage());
		}
		//Select Region
		try {
			WebElement region_dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-zone")));
			Select region = new Select(region_dropdown);
			//select1.selectByContainsVisibleText("Buesnos Aires");
	
			List<WebElement> options = region.getOptions();
			
			if (options == null) {
				System.out.println("List of Region is empty.");
			}
			else {
				region.selectByIndex(0);
				System.out.println("Region/State Input successful.");
				try {
					driver.findElement(By.id("button-quote")).click(); //Click "Get Quotes"
					driver.findElement(By.xpath("//div[@class='modal-dialog']"));
					WebElement radio_button = driver.findElement(By.name("shipping_method"));
					radio_button.click();
					driver.findElement(By.id("button-shipping")).click();//Click Apply of alert
					alert();
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("Dialogue box not shown.");
				}
				System.out.println("Apply Estimate Shipping & Taxes Sucessful.");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
}
