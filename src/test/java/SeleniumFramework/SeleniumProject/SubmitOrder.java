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

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.CheckOutPage;
import SeleniumFramework.PageObjects.ConfirmationPage;
import SeleniumFramework.PageObjects.LandingPage;
import SeleniumFramework.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		LandingPage landingPage=new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("soumya@email.com","Register@11");
		
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		List<WebElement> products=productCatalogue.getProductList();
		
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();
	
		CartPage cartPage =new CartPage(driver);
		Boolean match=cartPage.verifyProductDisplay(productName);
	    Assert.assertTrue(match);
	    
	    cartPage.goToCheckout();
	    
	    CheckOutPage checkoutPage=new CheckOutPage(driver);
	    checkoutPage.selectCountry("India");
	    checkoutPage.submitOrder();
	    
	    
	    ConfirmationPage confirmationPage=new ConfirmationPage(driver);
	    
	
	String confirmsg=confirmationPage.verifyConfirmationMessage();
	
	Assert.assertTrue(confirmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
	
	
	
	}

}
