package test_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class AddToCart extends RegisterAccount
{
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
	
	
	@Test(priority = 1)
	public void clickTheItem() 
	{
		WebElement item = driver.findElement(By.xpath("//div[@class='image']//img[@title='MacBook']")); 
		//WebElement item = driver.findElement(By.xpath("//div[@class='caption']//a[contains(text(),'MacBook')]")); 
		wait.until(ExpectedConditions.visibilityOf(item)); // Find an item
		item.click(); //Click the item
		
		addToCartButton();
	}
	
	public void addToCartButton() 
	{
		// Add to cart on the item page
		WebElement input_quantity = driver.findElement(By.id("input-quantity"));
		wait.until(ExpectedConditions.elementToBeClickable(input_quantity));
		input_quantity.clear();
		input_quantity.sendKeys("2");
		
		WebElement add_to_cart_btn = driver.findElement(By.id("button-cart"));
		wait.until(ExpectedConditions.elementToBeClickable(add_to_cart_btn));
		
		add_to_cart_btn.click();	
		//handleAlert();
		alert();
		System.out.println("Item add from item page successful.");
	}
	
	public void addToCartAfterSearch() 
	{
		//WebElement add_to_cart_btn1 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/button[1]"));
		// /html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/button[1]/i[1]	
		
		WebElement add_to_cart_btn1;
		String xpath_of_add_to_cart_btn1 = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/button[1]";
		String xpath_of_add_to_cart_btn2 = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/button[1]/i[1]";
		
		try {
			add_to_cart_btn1 = driver.findElement(By.xpath(xpath_of_add_to_cart_btn1));
		}
		catch (Exception e) {
			// TODO: handle exception
			add_to_cart_btn1 = driver.findElement(By.xpath(xpath_of_add_to_cart_btn2));
		}
		
		add_to_cart_btn1.click();
		System.out.println("Item added after Search");
	}		
	
	
}
