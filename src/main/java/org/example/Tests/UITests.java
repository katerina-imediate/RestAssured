package org.example.Tests;


import org.example.Pages.AddUser;
import org.example.Pages.ContactList;
import org.example.Pages.Login;
import org.example.Utils.Factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SuiteRunState;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class UITests {

    private static final Logger log = LoggerFactory.getLogger(UITests.class);
    private Map<String, String> user;
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        user = Factory.getRandomUser();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        Login loginPage = new Login(driver);
        loginPage.open();
        loginPage.assertIsLoaded();
    }

    @Test
    public void happyPathTest() {
        Login loginPage = new Login(driver);
        loginPage.navigateToAddUserPage();

        AddUser addUserPage = new AddUser(driver);
        addUserPage.assertIsLoaded();

        addUserPage.addNewUser(user);

        ContactList contactListPage = new ContactList(driver);
        contactListPage.assertIsLoaded();

        contactListPage.logout();
        loginPage.assertIsLoaded();
    }

    @Test
    public void invalidPathTest() {
        Login loginPage = new Login(driver);
        loginPage.navigateToAddUserPage();

        AddUser addUserPage = new AddUser(driver);
        addUserPage.addInvalidUser();
//        ContactList contactListPage = new ContactList(driver);
//        contactListPage.assertIsLoaded();

        addUserPage.errorUser();
    }
//User validation failed: email: Email is invalid
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}