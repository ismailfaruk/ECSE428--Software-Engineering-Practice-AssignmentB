package com.mustafakha.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import cucumber.api.java.en.Then;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features",glue={"StepDefinitions"})
public class StepDefinitions {

	private final String COMPOSE_MAIL = ".aic .z0 div";
	private final String RECIPIENT = ".oj div textarea";
	private final String MAIL_TEXT = ".Ar.Au div";
	private final String SUBJECT = "aoT";
	private final String ATTACH_CSS = "tr.btC td:nth-of-type(4) div div:nth-of-type(1)";
	private final String SUBMIT_CSS = "tr.btC td:nth-of-type(1) div div:nth-of-type(2)";
    private final String CONFIRMATION_POPUP_CLASS = "aT";
	private final String IMAGE_ATTACH_SCRIPT = "C:\\Users\\musta\\Desktop\\428assbtests\\fileUpload.exe";
	private final String VIDEO_ATTACH_SCRIPT = "C:\\Users\\musta\\Desktop\\428assbtests\\videoUpload.exe";
	private final String MULTIPLE_ATTACH_SCRIPT = "C:\\Users\\musta\\Desktop\\428assbtests\\fileAndVideo.exe";
	private final String ERROR_ATTACH_SCRIPT = "C:\\Users\\musta\\Desktop\\428assbtests\\errorFlow2.exe";
    private final String RECIPIENT_ID = ":8s";
    private final String RECIPIENT_CLASS = ":8s";
    private final String SUBJECT_CLASS = "aoT";
	private File file = new File("C:\\Users\\musta\\Downloads\\chromedriver_win32\\chromedriver.exe");
	private WebDriver driver;
	
	@Before
	public void setUpDriversBefore() {
		System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
		driver = new ChromeDriver();
	}


	
  @Given("^I am on the Gmail homepage using Google Chrome$")
  public void launch_google_chrome() throws Throwable {
		driver.get("http://www.gmail.com");
		driver.manage().window().maximize();
		
  }
  @And("^I log in with valid credentials$")
  public void log_in() throws Throwable {
	    driver.findElement(By.name("identifier")).sendKeys("robsabrox@gmail.com");
		driver.findElement(By.xpath(".//*[@id ='identifierNext']/content/span")).click();
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("password")).sendKeys("robsabrox123");
		driver.findElement(By.xpath(".//*[@id ='passwordNext']/content")).click();
  }
  
  @When("^I compose a new email$")
  public void fillFields() {
      WebElement compose = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(COMPOSE_MAIL)));
      compose.click();
      WebElement recipient = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(RECIPIENT)));
      recipient.sendKeys("robsabrox@gmail.com");
      WebElement mailText = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(MAIL_TEXT)));
      mailText.sendKeys("I think you'll like this");;
      WebElement subject = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.className(SUBJECT)));
      subject.sendKeys("428 TEST");
  }

//  @When("^I compose a new email addressed to someone else$")
//  public void write_email() throws Throwable {
//		driver.findElement(By.cssSelector(".aic .z0 div")).click();
//		driver.findElement(By.cssSelector(".oj div textarea")).sendKeys("mustafa.khawaja@mail.mcgill.ca");
//		driver.findElement(By.cssSelector(".Ar.Au div")).sendKeys("I thought you might like these");
//		driver.findElement(By.cssSelector("tr.btC td:nth-of-type(4) div div:nth-of-type(1)")).click();
//  }
  
//  @When("^I compose a new email addressed to myself$")
//  public void write_email_to_self() throws Throwable {
//		driver.findElement(By.cssSelector(".aic .z0 div")).click();
//		driver.findElement(By.cssSelector(".oj div textarea")).sendKeys("robsabrox@gmail.com");
//		driver.findElement(By.cssSelector(".Ar.Au div")).sendKeys("I thought you might like these");
//		driver.findElement(Bys.cssSelector("tr.btC td:nth-of-type(4) div div:nth-of-type(1)")).click();
//  }
//  
  @And("^attach an image$")
  public void attach_image() throws Throwable {
	    WebElement attach = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ATTACH_CSS)));
	    attach.click();
	    Runtime.getRuntime().exec(IMAGE_ATTACH_SCRIPT);
  }
  
  @And("^attach a video$")
  public void attach_video() throws Throwable {
	    WebElement attach = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ATTACH_CSS)));
	    attach.click();
	    Runtime.getRuntime().exec(VIDEO_ATTACH_SCRIPT);
  }
  
  @And("^attach a video and image$")
  public void attach_video_and_image() throws Throwable {
	    WebElement attach = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ATTACH_CSS)));
	    attach.click();
	    Runtime.getRuntime().exec(MULTIPLE_ATTACH_SCRIPT);  }

  @Then("^I will send the email successfully$")
  public void then() throws Throwable {
//		driver.findElement(By.cssSelector("tr.btC td:nth-of-type(1) div div:nth-of-type(2)")).click();
	    WebElement sendBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SUBMIT_CSS)));
        sendBtn.click();
        System.out.println("Send button clicked");
        WebElement statusPopup = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className(CONFIRMATION_POPUP_CLASS)));
        try {
            while(statusPopup.getText().contains("Sending"));
        } catch (Exception e){
            statusPopup = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className(CONFIRMATION_POPUP_CLASS)));
        }
        System.out.println("Email Sent!!!");
  }

  @And("^you are in And annotation$")
  public void and() throws Throwable {
  }

  
  /*HELPERS*/
  //This method returns a random String element from an array of Strings
  private String returnRandom(String[] inputList){
      Random random = new Random();
      int index = random.nextInt(inputList.length);
      return inputList[index];
  }
}
