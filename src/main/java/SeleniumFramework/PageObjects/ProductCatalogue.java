package SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFramework.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	//List<WebElement> products=driver.findElements(By.className("mb-3"));
	
	//PageFactory
	@FindBy(className="mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productsBy=By.className("mb-3");
	By addToCart=By.cssSelector(".w-10");
	By toastMessage=By.id("toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod=	getProductList().stream().filter(product->product.findElement(By.xpath("//div/h5/b")).getText().equals(productName)).findFirst().orElse(null);
	
		return prod;
	}
	
	public void addProductToCart(String productName) {
	
	WebElement prod=getProductByName(productName);
	prod.findElement(addToCart).click();
	waitForElementToAppear(toastMessage);
	waitForElementToDisAppear(spinner);
	
	}
}
