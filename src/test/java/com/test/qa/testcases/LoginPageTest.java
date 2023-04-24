package com.test.qa.testcases;

import com.test.qa.base.TestBase;
import com.test.qa.pages.HeaderLinks;
import com.test.qa.pages.LoginPage;
import com.test.qa.util.CommonMethods;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.test.qa.extentreports.ExtentTestManager.startTest;

public class LoginPageTest extends TestBase {

    LoginPage loginPage;
    HeaderLinks headerLinks;

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        headerLinks = new HeaderLinks();
    }

    @Test(description = "Verify that user is able to login into app with valid email and password")
    public void verifyLoginFunctionalityWithValidCredentials(Method method) {
        startTest(method.getName(),"Verify that user is able to login into app with valid email and password");
        headerLinks.verifyClickOnSignInLink();
        Assert.assertTrue(loginPage.verifySignInPageHeading());
        loginPage.verifyLogin(CommonMethods.getJsonValue("validEmailAddress"), CommonMethods.getJsonValue("validPassword"));
        Assert.assertTrue(headerLinks.verifyPresenceOfNewArticle());
    }

    @Test(description = "Verify that user is not able to sign in with invalid credentials")
    public void verifyLoginFunctionalityWithInvalidCredentials(Method method) {
        startTest(method.getName(),"Verify that user is not able to sign in with invalid credentials");
        headerLinks.verifyClickOnSignInLink();
        Assert.assertTrue(loginPage.verifySignInPageHeading());
        loginPage.verifyLogin(CommonMethods.getJsonValue("validEmailAddress"), CommonMethods.getJsonValue("invalidPassword"));
        Assert.assertTrue(loginPage.verifySignInErrorMsg());
    }

    @Test(description = "Verify that user is not able to sign in with blank username and password fields")
    public void verifyLoginFunctionalityWithBlankFields(Method method) {
        startTest(method.getName(),"Verify that user is not able to sign in with blank username and password fields");
        headerLinks.verifyClickOnSignInLink();
        Assert.assertTrue(loginPage.verifySignInPageHeading());
        loginPage.verifyLogin("","");
        Assert.assertTrue(loginPage.verifySignInPageHeading());
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
