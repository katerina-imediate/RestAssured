package org.example.Pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebdriverPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public WebdriverPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public abstract void assertIsLoaded();

}
