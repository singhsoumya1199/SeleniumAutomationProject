package SeleniumFramework.SeleniumProject;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumFramework.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ECommerceApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("soumya@email.com");
		driver.findElement(By.id("userPassword")).sendKeys("Register@11");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
		List<WebElement> products=driver.findElements(By.className("mb-3"));
		
	WebElement prod=	products.stream().filter(product->product.findElement(By.xpath("//div/h5/b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".w-10")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	List<WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	driver.findElement(By.xpath("(//button[normalize-space()='Checkout'])[1]")).click();
	
	Actions a=new Actions(driver);
	a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "ind").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
	driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
	
	String confirmsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
	
	
	
	}

}
