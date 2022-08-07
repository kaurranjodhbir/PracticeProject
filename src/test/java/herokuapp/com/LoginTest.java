package herokuapp.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

	public WebDriver driver;
	
	
  @BeforeMethod(alwaysRun=true)
	public void setup() {
		// Launch Chrome driver

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("driver launched");

//maximize browser
		driver.manage().window().maximize();

	}

  @Test(groups="positiveTest")
	public void positiveTest() {

//to sleep
		sleep(3000);

//open URL
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("page is opened");

		// enter username

		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(1000);

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(1000);

		WebElement loginButton = driver.findElement(By.className("radius"));
		loginButton.click();
		sleep(1000);

		// Validations
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualURL, "actual url is not same as expected");

		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "no logout button ");

		String expectedMessage = "You logged into a secure area!";
		WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
		String actualMessage = successMessage.getText();
		// Assert.assertEquals(expectedMessage,actualMessage,"actual message is not same
		// as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage), "they are not same");

		// to close browser

		

	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Parameters({"username","password","expectedMessage"})
	@Test (priority =2)
	
	public void negtiveTest(String username,String password,String expectedMessage)
	
	{
		
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
		
	}
	  @AfterMethod
		public void shutBrowser()
		{
			driver.quit();
		}
	

}
