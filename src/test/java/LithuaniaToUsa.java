import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class LithuaniaToUsa {
    private WebDriver driver;

    @Given("location is Lithuania")
    public void location_is_lithuania() {
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
        driver.get("https://www.westernunion.com/lt/en/home.html");
    }
    @When("changing location settings")
    public void changing_location_settings() {
        //wait for cookies banner
        WebDriverWait waitCookies = new WebDriverWait(driver,5);
        waitCookies.until(ExpectedConditions.presenceOfElementLocated (By.id("onetrust-consent-sdk")));

        WebElement trustBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
        trustBtn.click();


        //wait for cookies banner to disappear
        waitCookies = new WebDriverWait(driver,5);


        //find menuicon and click it
        WebElement burgerSymbol = driver.findElement(By.id("menuicon"));
        waitCookies.until(ExpectedConditions.elementToBeClickable (burgerSymbol));
        burgerSymbol.click();

        //wait for menu-settings to appear
        WebDriverWait waitBurger = new WebDriverWait(driver,5);

        //find menu-settings and click it
        //TODO:maybe there is a better selector for this ?
        WebElement settingsButton = driver.findElement(By.xpath("//li[@amplitude-id='menu-settings']"));
        waitCookies.until(ExpectedConditions.elementToBeClickable (settingsButton));
        settingsButton.click();

        //wait for settings page to load
        WebDriverWait waitSettingsPage = new WebDriverWait(driver,5);
        waitSettingsPage.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("Question")));
        Select question = new Select(driver.findElement(By.id("Question")));
        question.selectByValue("object:252");

        //wait for confirmation button
        //TODO:maybe there is a better selector for this ?
        WebElement confirmButton = driver.findElement(By.cssSelector(".info-popup-container button:not(.btn-popup-negative)"));
        confirmButton.click();
    }
    @Then("location should be UnitedStates")
    public void location_should_be_united_states() {
        //make sure page is United States Page
        Assert.assertEquals("https://www.westernunion.com/us/en/home.html", driver.getCurrentUrl());

        driver.quit();
    }
}
