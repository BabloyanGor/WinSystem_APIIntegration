//package pageObjects;//package pageObjects;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.CacheLookup;
//import org.openqa.selenium.support.FindBy;
//
//import java.awt.*;
//
//public class WinSystem_UI_Elements extends BasePage {
//    private final BasePage basePage;
//
//    public WinSystem_UI_Elements(WebDriver driver) throws AWTException {
//        super(driver);
//        basePage = new BasePage(driver);
//    }
//
//    @FindBy(xpath = "//button[@class='button-style2-type-btn global_login-btn pointer']")
//    @CacheLookup
//    WebElement loginButtonMain;
//
//    public void clickLoginButtonMain() {
//        try {
//            basePage.waitElementToBeVisible(loginButtonMain);
//            basePage.javaScriptClick(loginButtonMain);
//        } catch (Exception e) {
//        }
//    }
//
//    @FindBy(xpath = "//input[@name='username']")
//    @CacheLookup
//    WebElement username;
//
//    public void sendKeysUsername(String Username) {
//        try {
//            basePage.sendKeysIfElementVisible(username,Username);
//        } catch (Exception e) {
//        }
//    }
//
//    @FindBy(xpath = "//input[@formcontrolname='Password']")
//    @CacheLookup
//    WebElement password;
//
//    public void sendKeysPassword(String Password) {
//        try {
//            basePage.sendKeysIfElementVisible(password,Password);
//        } catch (Exception e) {
//        }
//    }
//
//    @FindBy(xpath = "//button[@class='craft_btn login_btn -btn']")
//    @CacheLookup
//    WebElement loginButtonPopUp;
//
//    public void clickLoginButtonPopUp() {
//        try {
//            basePage.waitElementToBeVisible(loginButtonPopUp);
//            basePage.javaScriptClick(loginButtonPopUp);
//        } catch (Exception e) {
//        }
//    }
//
//}
