package test_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class AddToCart extends DriverSetup
{
	//AlertMessagePrint alert = new AlertMessagePrint();
	private static String item_xpath = "//img[@title='MacBook']";
	public boolean isProductPresent(){
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item_xpath)));
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
	
	@Test (priority = 1)
	public void clickTheItem() {
		/*System.out.println("Driver instance in AddToCart: " + driver.hashCode());
		System.out.println("Driver instance in AlertMessagePrint: " + AlertMessagePrint.driver.hashCode());
*/
		if (isProductPresent() == true) {
			clickItem();
		}
		else {
			item_xpath = "//a[normalize-space()='MacBook Pro']";
			clickItem();
		}
	}
	
	public void clickItem() {
		//WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item_xpath))); // Find an item
		WebElement item = driver.findElement(By.xpath(item_xpath));
		item.click(); //Click the item
		System.out.println("Navigated to product page.");
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
		//alert.alert();
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
