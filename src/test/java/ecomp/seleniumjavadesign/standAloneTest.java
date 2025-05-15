package ecomp.seleniumjavadesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("poojarao123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pooja@123");
		driver.findElement(By.id("login")).click();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(11));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.card-body")));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		List<WebElement> products = driver.findElements(By.cssSelector("div.card-body"));
		System.out.println("total products found: " + products.size());
		String productName = "ZARA COAT 3";
		WebElement prod = null;

		for (WebElement product : products) {
			String name = product.findElement(By.cssSelector("b")).getText();
			if (name.equalsIgnoreCase(productName)) {
				prod = product;
				break;
			}
		}

//   if(prod!=null) {
//	   prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//	   System.out.println("Clicked on Add to cart for "+ productName);
//   }
//   else {
//	   System.out.println("product not found");
//   }
//   

		if (prod != null) {
			WebElement addToCartBtn = prod.findElement(By.cssSelector("button.w-10"));
			wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
			js.executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
			js.executeScript("arguments[0].click();", addToCartBtn);
			System.out.println("Clicked on Add to Cart for: " + productName);
		} else {
			System.out.println("Product not found: " + productName);
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		// wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	   //[routerlink*='cart']
//	   driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		WebElement cartbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
		js.executeScript("arguments[0].click();", cartbutton);
//cartbutton.click();

//cartproductlist

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		//list of items in cart
		cartProducts.forEach(prods -> System.out.println("cart item" + prods.getText()));
//anyMatch 
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		Assert.assertTrue(match, "product not found in the cart" + productName);
//System.out.println(cartProduct);
		WebElement checkOutButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkOutButton);
		Thread.sleep(1000);
//added below javascriptexecutor bcz it was giving error "ElementClickInterceptedException
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkOutButton);
//checkOutButton.click();

		//sending shippinginformation
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build()
				.perform();

		//wait until dropwon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		//click on dropdown country
		WebElement countrydrop = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@class,'ta-item')])[2]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countrydrop);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", countrydrop);
//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		//PlaceOrder button
		WebElement placeOrderButton = driver.findElement(By.cssSelector(".action__submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderButton);
		//ThankYouPage
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmMessage);
	}

}
//WebElement prod =	products.stream()
//.filter(product->
//product.findElement(By.cssSelector("b")).getText().equals(productName))
//.findFirst().orElse(null);
//if(prod==null) {
//System.out.println("product not found");
//return;
//}
//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

//WebElement prod= products.stream().filter(product -> 
//product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
//
//
//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();