package org.swaglab_login;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class LoginTest extends LoginPage{

    WebDriver driver;

    @BeforeMethod
    void setUp()
    {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }

    @Test(groups = {"UI Test"})
    public void testLogoDisplayed()
    {
        WebElement logo = driver.findElement(By.cssSelector(LOGO_SELECTOR_CSS));
        Assertions.assertThat(logo.isDisplayed()).isTrue();
    }



    @Test(groups = {"UI Test"})
    public void testBotDisplayed()
    {
        WebElement bot = driver.findElement(By.cssSelector(BOT_SELECTOR_CSS));
        Assertions.assertThat(bot.isDisplayed()).isTrue();
    }

    @Test(groups = {"UI Test"})
    public void testLoginDisplayed()
    {
        WebElement loginButton = driver.findElement(By.id(LOGIN_SELECTOR_ID));
        Assertions.assertThat(loginButton.isDisplayed()).isTrue();
    }

    @Test(groups = {"UI Test"})
    public void testUserNameFieldDisplayed()
    {
        WebElement userNameField = driver.findElement(By.cssSelector(USER_NAME_SELECTOR_CSS));
        Assertions.assertThat(userNameField.isDisplayed()).isTrue();
    }

    @Test(groups = {"UI Test"})
    public void testPasswordFieldDisplayed()
    {
        WebElement userNameField = driver.findElement(By.cssSelector(PASSWORD_SELECTOR_CSS));
        Assertions.assertThat(userNameField.isDisplayed()).isTrue();
    }

    @Test(groups = {"UI Test"})
    public void testPageTitle()
    {
        Assertions.assertThat(driver.getTitle()).isEqualTo("Swag Labs");
    }


    private void fillTheFields(String userName, String password)
    {
        WebElement userNameField = driver.findElement(By.cssSelector(USER_NAME_SELECTOR_CSS));
        WebElement passwordField = driver.findElement(By.cssSelector(PASSWORD_SELECTOR_CSS));
        WebElement loginButton = driver.findElement(By.id(LOGIN_SELECTOR_ID));
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @Test(groups = {"login_test"})
    public void testLoginValidCredentials()
    {
        fillTheFields("standard_user", "secret_sauce");
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo("https://www.saucedemo.com/inventory.html");
        Assertions.assertThat(driver.findElement(By.cssSelector(".title")).getText()).isEqualTo("PRODUCTS");
    }

    public void testLoginEmptyUsername()
    {
        fillTheFields("", "secret_sauce");
    }


}