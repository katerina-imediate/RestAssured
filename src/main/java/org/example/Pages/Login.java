package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.example.Utils.Env;


public class Login extends WebdriverPage {
    private static final By SIGNUP_BUTTON = By.id("signup");

    public Login(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.navigate().to(Env.HEROKU);
    }

    public void navigateToAddUserPage() {
        driver.findElement(SIGNUP_BUTTON).click();
    }

    @Override
    public void assertIsLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(SIGNUP_BUTTON));
    }
}
