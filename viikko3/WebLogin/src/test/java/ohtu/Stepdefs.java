package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
          goToDestination("login");
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @When("unregistered username {string} and password {string} are given")
    public void unregisteredUsernameGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @Given("command new user is selected")
    public void newUserIsSelected() {
        goToDestination("register new user"); 
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndValidPasswordAreGiven(String username, String password) {
        registerWith(username, password, password);
    }

    @Then("a new user is created")
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application");
    }

    @When("an invalid username {string} and a valid password {string} and matching password confirmation are entered")
    public void invalidUsernameIsGiven(String username, String password) {
        registerWith(username, password, password);
    }

    @When("a valid username {string} and an invalid password {string} and matching password confirmation are entered")
    public void invalidPasswordIsGiven(String username, String password) {
        registerWith(username, password, password);
    }

    @When("a valid username {string} and password {string} and an unmatching password confirmation {string} are entered")
    public void passwordsDoNotMatch(String username, String password, String passwordConfirmation) {
        registerWith(username, password, passwordConfirmation);
    }

    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorIsReported(String error) {
        pageHasContent(error);
        pageHasContent("Create username and give password");
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userSuccessfullyCreated(String username, String password) {
        createAccountAndLogout(username, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void failToCreateUser(String username, String password) {
        failToCreateAccountAndReturnToIndex(username, password);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        pageHasContent("Give your credentials to login");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 

    private void goToDestination(String destination) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText(destination));       
        element.click(); 
    }

    private void registerWith(String username, String password, String passwordConfirmation) {
        pageHasContent("Create username and give password");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit(); 
    }

    private void createAccountAndLogout(String username, String password) {
        goToDestination("register new user"); 
        registerWith(username, password, password);
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }

    private void failToCreateAccountAndReturnToIndex(String username, String password) {
        goToDestination("register new user");
        registerWith(username, password, password);
        WebElement element = driver.findElement(By.linkText("back to home"));       
        element.click(); 
    }
}
