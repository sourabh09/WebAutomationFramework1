package com.test.qa.util;

import com.test.qa.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v102.media.model.Timestamp;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;

public class CommonMethods extends TestBase {

    /** Wait till element is visible **/
    public static void waitTillElementVisible(WebElement element){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        Log.info("Waiting for element to present : "+ element);
    }

    /** Clicking on a web element **/
    public static void clickOnElement(WebElement element){
        waitTillElementVisible(element);
        element.click();
        Log.info("Clicking on element : "+ element);
    }

    /** Check for element presence on page **/
    public static boolean isElementPresent(WebElement element){
        try {
            waitTillElementVisible(element);
            element.isDisplayed();
            Log.info(element+ " is displayed on the page");
            return true;
        }catch (NoSuchElementException e){
            e.printStackTrace();
            Log.error(element+ " is not displayed on the page");
            return false;
        }
    }

    /** Enter text in a field **/
    public static void enterTxtInElement(WebElement element, String text){
        waitTillElementVisible(element);
        element.clear();
        element.sendKeys(text);
        Log.info(text +" has been entered in field " +element);
    }

    /** Get and return text of a web element **/
    public static String getTxtOfElement(WebElement element){
        waitTillElementVisible(element);
        Log.info("Text has been get and returned for : "+element);
        return element.getText();
    }

    /** Get and return title of webpage **/
    public static String getTitleOfPage(){
        return driver.getTitle();
    }

    /** Reading data from JSON **/

    public static String getJsonValue(String key) {

        String jsonFilePath = System.getProperty("user.dir") + "//src//main//java//com//test//qa//testdata//testdata.json";
        String returnValue = null;

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(jsonFilePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            returnValue = (String) jsonObject.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return returnValue;

    }

    /**Get and return current timestamp **/
    public static String getCurrentTimestamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       return String.valueOf(timestamp);
    }

    /** Capture and save screenshot **/
    public static void takeScreenshot(){
        String path = System.getProperty("user.dir")+"//TestReport//screenshots//";
       File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       String filePath =path + "screenshot" +getCurrentTimestamp()+ ".png";
        try {
            FileUtils.copyFile(file, new File(filePath));
            Log.info("Screenshot has been captured and saved in path : "+path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}