package test_website;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SearchItems extends DriverSetup
{
	AddToCart add = new AddToCart();
	public List<String> items() {
		List<String> item = Arrays.asList("iMac", "iPhone", "mac");
		return item;
	}
	//public static String item2 = "iPhone";
	
	@Test(priority = 3)
	public void searchItems() 
	{
		//To search item and directly add to cart
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		List<String> sublist = items().subList(0, 2); // This will get items at index 0 and 1
		for(String item : sublist) {
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search']"));
			search.clear();
			search.sendKeys(item); // Send the item to the Search Field
			
			WebElement search_icon = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
			search_icon.click(); //Click the Search Button
			
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']")));
			
	        add.addToCartAfterSearch();
	        
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));  
		}	 
		searchAnItem();
	}
	
	public void searchAnItem() 
	{
		// To search an item and check sort filters and then go to item then add to cart
		
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		search.clear();
		search.sendKeys(items().get(2));
		
		WebElement search_icon = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
		search_icon.click(); //Click the search icon
		System.out.println("Search an item success.");
		
		list_grid_view();
		
		filter_check();
		
		add.clickTheItem();
		
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	 }
	
	public void list_grid_view() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-th-list']"))).click();
		System.out.println("List View Success");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-th']"))).click();
		System.out.println("Grid View Success");
	}
	
	public void filter_check() {
		driver.findElement(By.id("description")).click(); //Click checkbox "Search in product descriptions"
		driver.findElement(By.id("button-search")).click(); //Click Search
		System.out.println("Search in product descriptions Success");
		
		//List<Integer> i = new ArrayList<>(List.of(0,1,2,3,4,5,6,7,8));
		for(int i=0;i<9;i++) {
			WebElement sort_dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-sort")));
			sort_dropdown.click();
			Select sort = new Select(sort_dropdown);
			sort.selectByIndex(i);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			//System.out.println("Sort " +i+" Success");
		}
		System.out.println("Sort Success");
	}
}
