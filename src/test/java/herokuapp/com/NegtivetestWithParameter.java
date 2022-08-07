package herokuapp.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class NegtivetestWithParameter {

	
	@Parameters({"username","password","expectedMessage"})
	@Test
	
	public void negtiveTest(String username,String password,String expectedMessage)
	
	{
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		
		System.out.println("Chrome driver launched ");
		
		driver.get("https://the-internet.herokuapp.com/login");
		
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys(password);
		
		WebElement loginButton=driver.findElement(By.className("radius"));
		loginButton.click();
		
		WebElement actualMessage = driver.findElement(By.id("flash"));
		String actualmsg=actualMessage.getText();
		//String expectedMessage = "Your password is invalid!";
		Assert.assertTrue(actualmsg.contains(expectedMessage),"it doesn't match");
		driver.quit();
		
	}
}
