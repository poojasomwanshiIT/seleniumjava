package ecomp.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductPage {

	//to use driver we need to create constructor as we already have driver in standalone class
	//so first create object of landingpage in stadalone class and pass the diver and then call it in landingpage constructor
	//create local variable driver
	WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		
		//to access all elements 
		PageFactory.initElements(driver, this);
	}

	//WebElement username=driver.findElement(By.id("userEmail"));
	//rewriting above code using PageFctory
	@FindBy(id="userEmail")
	WebElement username;
	
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement password;
	
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submitButton;
	
	public void loginApplication(String email,String pass) {
		
		username.sendKeys(email);
		password.sendKeys(pass);
		submitButton.click();
		
	}
	
	//invoking url
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
