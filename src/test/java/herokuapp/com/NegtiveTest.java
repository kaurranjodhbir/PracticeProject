package herokuapp.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegtiveTest {

	
	  @Test(priority=1, groups={"NegtiveTests","SmokeTest"})
	  public void incorrectUsernameTest() {
	  System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe"); 
	  WebDriver driver = new ChromeDriver(); 
	  System.out.println("driver launched");
	  
	  driver.manage().window().maximize();
	  
	  driver.get("https://the-internet.herokuapp.com/login");
	  
	  WebElement username = driver.findElement(By.name("username"));
	  username.sendKeys("incorrectUsername");
	  
	  WebElement password = driver.findElement(By.name("password"));
	  password.sendKeys("SuperSecretPassword!");
	  
	  WebElement loginButton = driver.findElement(By.className("radius"));
	  loginButton.click();
	  
	  
	 
	  WebElement failureMessage = driver.findElement(By.id("flash"));
	  String actualMessage = failureMessage.getText();
	  String expectedmessage =  "Your username is invalid!";
	  Assert.assertTrue(actualMessage.contains(expectedmessage),"it doesn't match");
	  
	  driver.quit();
	  
	  }
	 
	
	@Test(priority=2 , groups= {"NegtiveTest"})
	public void incorrectPassword()
	
	{
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new  ChromeDriver();
		driver.manage().window().maximize();
		  
		
		driver.get("https://the-internet.herokuapp.com/login");
		
		WebElement  username= driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("123");
		
		WebElement loginButton = driver.findElement(By.className("radius"));
		loginButton.click();
		
		
		WebElement passwordFailureMessage = driver.findElement(By.id("flash"));
		String expectedMessage = "Your password is invalid!";
		String actualMessage = passwordFailureMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage), "it's incorrect");
		
		driver.quit();		
	}

}
