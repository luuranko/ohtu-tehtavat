package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        sleep(1);
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        Random r = new Random();
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
        int amountOfLetters = r.nextInt(5)+2;
        StringBuilder append = new StringBuilder();
        for (int i = 0; i < amountOfLetters; i++) {
            append.append(letters[r.nextInt(letters.length)]);
        }
        String username = "pekka"+append.toString();
        String password = "akkep2000";
        register(driver, username, password);

        
        sleep(2);

        logout(driver);

        sleep(2);

        login(driver, username, "pekka1000");

        sleep(2);
        
        tryLoginAgain(driver, username, password);

        driver.quit();
    }

    private static void login(WebDriver driver, String username, String password) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();
    }

    private static void tryLoginAgain(WebDriver driver, String username, String password) {
        sleep(1);

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();
        sleep(3);
    }

    private static void register(WebDriver driver, String username, String password) {
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        sleep(2);
        element.submit();
        sleep(1);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
    }

    private static void logout(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("logout"));
        sleep(1);
        element.click();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
