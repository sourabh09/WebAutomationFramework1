package com.test.qa.pages;

import com.test.qa.base.TestBase;
import com.test.qa.util.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderLinks extends TestBase {

    @FindBy(partialLinkText = "Sign in")
    public WebElement signInLink;

    @FindBy(xpath = "//a[@href='#/editor/']")
    public WebElement newArticleLink;

    public HeaderLinks(){
        PageFactory.initElements(driver,this);
    }

    public void verifyClickOnSignInLink(){
        CommonMethods.clickOnElement(signInLink);
    }

    public boolean verifyPresenceOfNewArticle(){
        return CommonMethods.isElementPresent(newArticleLink);
    }
}
