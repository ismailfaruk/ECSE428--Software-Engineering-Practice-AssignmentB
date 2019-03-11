package com.mustafakha.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import cucumber.api.java.en.Then;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
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

    private final String INSERT_PHOTO = ":gw";
    private final String URL_CLASS = "a-Cf a-Cf-V";
    private final String URL_INPUT_CLASS = "Mf-Tl-Qc";
    private final String INSERT_BTN = "picker:ap:2";
	private final String HOME_PAGE1 = "https://mail.google.com/mail/";
	private final String HOME_PAGE = "https://mail.google.com/mail/#inbox";
	private final String SIGN_IN_PATH = ".//*[@id ='identifierNext']/content/span";
	private final String PASS_BTN_PATH = ".//*[@id ='passwordNext']/content";
	private final String COMPOSE_MAIL = ".aic .z0 div";
	private final String RECIPIENT = ".oj div textarea";
	private final String MAIL_TEXT = ".Ar.Au div";
	private final String SUBJECT = "aoT";
	private final String ATTACH_CSS = "tr.btC td:nth-of-type(4) div div:nth-of-type(1)";
	private final String SUBMIT_CSS = "tr.btC td:nth-of-type(1) div div:nth-of-type(2)";
	private final String ATTACHED_SUCCESSFULLY = "dL";
    private final String CONFIRMATION_POPUP_CLASS = "aT";
    private final String LOGOUT_CSS = "span.gb_ya.gbii"; //TOP right icon
    private final String SIGNOUT_ID = "gb_71";
	private final String IMAGE_ATTACH_SCRIPT = "C:\\Users\\musta\\Desktop\\428assbtests\\fileUpload.exe";
	private final String VIDEO_ATTACH_SCRIPT = "C:\\Users\\musta\\Desktop\\428assbtests\\videoUpload.exe";
	private final String MULTIPLE_ATTACH_SCRIPT = "C:\\Users\\musta\\Desktop\\428assbtests\\fileAndVideo.exe";
	private final String ERROR_ATTACH_SCRIPT = "C:\\Users\\musta\\Desktop\\428assbtests\\errorFlow2.exe";
	private final String TEST_URL = "https://www.vergify.com/blog/wp-content/uploads/2017/07/automation-1.png";
	//MY local chromedriver.exe location, please edit to wherever you have downloaded yours.
	private File file = new File("C:\\Users\\musta\\Downloads\\chromedriver_win32\\chromedriver.exe");
	//Fake Recipients
    private final String[] recipients = {"robsabrox@gmail.com", "iloveautomation@gmail.com", "seleniumfangirl@gmail.com", "outofnames@gmail.com", "mustafa.khawaja@mail.mcgill.ca"};
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
	  WebElement identifier = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.name("identifier")));
	  identifier.sendKeys("robsabrox@gmail.com");
	  WebElement nextBtn = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.xpath(SIGN_IN_PATH)));
	  nextBtn.click();
	  WebElement passField = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
	  passField.sendKeys("robsabrox123");
	  WebElement nextBtn2 = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.xpath(PASS_BTN_PATH)));
	  nextBtn2.click();
  }
  
  @When("^I compose a new email$")
  public void fillFields() {
      WebElement compose = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(COMPOSE_MAIL)));
	  
	  //INITIAL STATE DEFINED BY HOME_PAGE URL, I.E we are in the inbox, about to compose
		System.out.println("System is in inital state: " + initialState());

      compose.click();
      WebElement recipient = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(RECIPIENT)));
      recipient.sendKeys(returnRandom(recipients));
      WebElement mailText = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(MAIL_TEXT)));
      mailText.sendKeys("I think you'll like this");;
      WebElement subject = (new WebDriverWait(driver, 5))
              .until(ExpectedConditions.presenceOfElementLocated(By.className(SUBJECT)));
      subject.sendKeys("428 Ass. B test");
  } 
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
	    Runtime.getRuntime().exec(MULTIPLE_ATTACH_SCRIPT);  
	    }
  @And("^send an attachment as a url$")
  public void attach_large_file() throws Throwable {
	  WebElement attach = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id(":9w")));//ID of links
	  attach.click();
	  WebElement url_input = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("linkdialog-onweb-tab-input")));
	  url_input.sendKeys(TEST_URL);
	  WebElement nextBtn = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.className("J-at1-auR")));
	  nextBtn.click();
  }

  @Then("^I will send the email successfully$")
  public void then() throws Throwable {
	  	//Use this to wait for the sendBtn, once attachment is successfully attached, then we can initiate/click button.
	    WebElement attached = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className(ATTACHED_SUCCESSFULLY)));
	    
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
        System.out.println("Email Sent");
        
        //Used to check if email successfully sent
        Assert.assertTrue(emailSentSuccessfully(statusPopup));
        returnToInitialState();
        
        //uncomment if you wish to sign out and close at the end of test
        //signOut();


  }
  
  @Then("^I will send the email successfully with a url$")
  public void url_mail() throws Throwable {
	    WebElement sendBtn = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SUBMIT_CSS)));
        sendBtn.click();
        System.out.println("Send button clicked");
        WebElement statusPopup = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.className(CONFIRMATION_POPUP_CLASS)));
        try {
            while(statusPopup.getText().contains("Sending"));
        } catch (Exception e){
            statusPopup = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className(CONFIRMATION_POPUP_CLASS)));
        }
        System.out.println("Email Sent");
        
        //Used to check if email successfully sent
        Assert.assertTrue(emailSentSuccessfully(statusPopup));
        returnToInitialState();
        
        //uncomment if you wish to sign out and close at the end of test
        //signOut();


  }

  @And("^attach a file that does not exist$")
  public void and() throws Throwable {
	    WebElement attach = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ATTACH_CSS)));
	    attach.click();
	    Runtime.getRuntime().exec(ERROR_ATTACH_SCRIPT);  
  }

  @Then("^I will not send the email$")
  public void errorFlow() throws Throwable{
	  returnToInitialState();
  }
  
  
  /*HELPERS*/
  
  private void signOut() {
      //Click on the profile image present in the right top corner
        driver.findElement(By.cssSelector(LOGOUT_CSS)).click();
        //Click on 'Sign Out' button
        driver.findElement(By.id(SIGNOUT_ID)).click();
        driver.close();
  }
  
  // This method ensures system always returns to initial state after an email is sent
  private void returnToInitialState(){
      if (initialState() != true){
          driver.get(HOME_PAGE);
          System.out.println("System was forced to return to initial state");
      }
      else{
          System.out.println("System has returned to initial state");
      }
  }
  //This method returns a random String element from an array of Strings
  private String returnRandom(String[] inputList){
      Random random = new Random();
      int index = random.nextInt(inputList.length);
      return inputList[index];
  }
  
  // This method returns true if we return to initial state after sending an email
  private boolean initialState(){
      return driver.getCurrentUrl().equals(HOME_PAGE) || driver.getCurrentUrl().equals(HOME_PAGE1);
  }
  
  //Used to check if email was correctly sent
  private boolean emailSentSuccessfully(WebElement messageSent){
      return messageSent.getText().contains("Message sent");
  }
}
