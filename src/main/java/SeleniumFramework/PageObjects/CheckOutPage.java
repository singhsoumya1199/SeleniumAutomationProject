package SeleniumFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFramework.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//a[normalize-space()='Place Order']")
	WebElement submit;
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	WebElement SelectCountry;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		
		waitForElementToAppear(results);
		SelectCountry.click();
	}
	
	public void submitOrder() {
		
		submit.click();
	}
	
	
	
}
