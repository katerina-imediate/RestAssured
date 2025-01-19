package org.example.Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Apis.User;
import org.example.Utils.Env;
import org.example.Utils.Factory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class APITests {
    private Map<String, String> user;

    @BeforeClass
    public void setup() {
        user = Factory.getRandomUser();
        RestAssured.baseURI = Env.HEROKU;
    }

    @Test
    public void addUserTest() {
        Response response = User.addUser(user);

        response.then().assertThat().statusCode(201);
    }

    @Test(dependsOnMethods = "addUserTest")
    public void loginUserTest() {
        Response response = User.loginUser(user);

        response.then().assertThat().statusCode(200);

        String token = response.jsonPath().get("_");

        user.put("_", token);

    }

    @Test(dependsOnMethods = "loginUserTest")
    public void logoutUserTest() {
        String token = user.get("token");
        Response response = User.logoutUser(token);

        response.then().assertThat().statusCode(200);
    }

    @Test(priority = 1)
    public void deleteUserTest() {
        loginUserTest();

        String token = user.get("token");
        Response response = User.deleteUser(token);

        response.then().assertThat().statusCode(200);
    }
}
