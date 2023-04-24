package com.test.qa.pages;

import com.test.qa.base.TestBase;
import com.test.qa.util.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    @FindBy(tagName = "h1")
    public WebElement signInPageHeading;

    @FindBy(css = "input[placeholder='Email']")
    public WebElement emailField;

    @FindBy(css = "input[placeholder='Password']")
    public WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    public WebElement signInBtn;

    @FindBy(xpath = "//li[contains(text(),'email or password is invalid')]")
    public WebElement signInErrorMsg;

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    public boolean verifySignInPageHeading(){
        return CommonMethods.isElementPresent(signInPageHeading);
    }

    public boolean verifySignInErrorMsg(){
        return CommonMethods.isElementPresent(signInErrorMsg);
    }

    public void verifyLogin(String email, String password) {

        CommonMethods.enterTxtInElement(emailField,email);
        CommonMethods.enterTxtInElement(passwordField, password);
        CommonMethods.clickOnElement(signInBtn);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
