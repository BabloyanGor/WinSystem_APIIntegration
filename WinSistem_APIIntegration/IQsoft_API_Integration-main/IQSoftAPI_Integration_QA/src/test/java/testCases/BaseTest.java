package testCases;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import testData.*;
import utilities.ReadConfig;

import java.time.Duration;


public class BaseTest {

    public BaseTest() {
    }

    public static String creditValidTransactionID = "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_C";

    public static String debitValidTransactionID = "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_D";

    public String randomCreditTransactionID() {
        return "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_C";
    }

    public String randomDebitTransactionID() {
        return "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_D";
    }

    public String randomRollBackTransactionID() {
        return "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_R";
    }










    //region <Request and Response Variables Integration>
    IqSoft_01_APIVariables_GetProductUrl_Request iqSoft02ApiVariables_getProductUrl_request = new IqSoft_01_APIVariables_GetProductUrl_Request();
    IqSoft_01_APIVariables_GetProductUrl_Response iqSoft01ApiVariables_getProductUrl_response = new IqSoft_01_APIVariables_GetProductUrl_Response();

    IqSoft_02_APISVariables_Authorization_Request iqSoft_02_apisVariables_authorization_request = new IqSoft_02_APISVariables_Authorization_Request();
    IqSoft_02_APISVariables_Authorization_Response iqSoft_02_apisVariables_authorization_response = new IqSoft_02_APISVariables_Authorization_Response();

    IqSoft_03_APIVariables_GetBalance_Request iqSoft_03_apiVariables_getBalance_request = new IqSoft_03_APIVariables_GetBalance_Request();
    IqSoft_03_APIVariables_GetBalance_Response iqSoft_03_apiVariables_getBalance_response = new IqSoft_03_APIVariables_GetBalance_Response();

    IqSoft_04_APIVariables_Credit_Request iqSoft_04_apiVariables_credit_request = new IqSoft_04_APIVariables_Credit_Request();
    IqSoft_04_APIVariables_Credit_Response iqSoft_04_apiVariables_credit_response = new IqSoft_04_APIVariables_Credit_Response();

    IqSoft_05_APIVariables_Debit_Request iqSoft_05_apiVariables_debit_request = new IqSoft_05_APIVariables_Debit_Request();
    IqSoft_05_APIVariables_Debit_Response iqSoft_05_apiVariables_debit_response = new IqSoft_05_APIVariables_Debit_Response();

    IqSoft_06_APIVariables_RollBack_Request iqSoft_06_apiVariables_rollBack_request = new IqSoft_06_APIVariables_RollBack_Request();
    IqSoft_06_APIVariables_RollBack_Response iqSoft_06_apiVariables_rollBack_response = new IqSoft_06_APIVariables_RollBack_Response();


    //endregion

    //region <Variables for gameLaunchURL API>

    public static Logger logger;
    ReadConfig readConfig = new ReadConfig();
    public String gameLaunchURL = readConfig.getGameLaunchURL();
    public String callbackUrl = readConfig.getCallbackUrl();
    public int partnerID = readConfig.getPartnerID();
    public int productID = readConfig.getProductID();

    public int clientProductID = readConfig.getClientProductID();

    public int clientId = readConfig.getClientId();
    public double betAmountCredit = readConfig.getBetAmountCredit();
    public double betAmountDebit = readConfig.getBetAmountDebit();
    public String currency = readConfig.getCurrency();

    public String userName = readConfig.getUserName();
    //endregion

    public HttpResponse<String> getUrlAPI(int PartnerID, int ProductID, int ClientID, String UserToken) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft02ApiVariables_getProductUrl_request.setPartnerId(PartnerID);
        iqSoft02ApiVariables_getProductUrl_request.setProductId(ProductID);
        iqSoft02ApiVariables_getProductUrl_request.setClientId(ClientID);
        iqSoft02ApiVariables_getProductUrl_request.setUserToken(UserToken);  //use String <userToken> if token will be passed from config

        String getProductUrlRequestBody = gson.toJson(iqSoft02ApiVariables_getProductUrl_request);
        logger.info("getURLRequestBody :" + getProductUrlRequestBody);

        long start = System.currentTimeMillis();
        HttpResponse<String> openGameResponse = Unirest.post(gameLaunchURL)
                .header("Content-Type", "application/json")
                .body(getProductUrlRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("OpenGameAPI RequestBody ", getProductUrlRequestBody);
        Allure.addAttachment("OpenGameAPI ResponseBody ", openGameResponse.getBody() + "  ResponseTime " + (end - start) + " ms");

        return openGameResponse;
    }


    public HttpResponse<String> authorizationAPI(String SessionToken, int ProductID) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_02_apisVariables_authorization_request.setToken(SessionToken);
        iqSoft_02_apisVariables_authorization_request.setProductId(ProductID);
        String authorizationRequestBody = gson.toJson(iqSoft_02_apisVariables_authorization_request);
        logger.info("AuthorizationRequestBody : " + authorizationRequestBody);

        long start = System.currentTimeMillis();
        HttpResponse<String> authorizationResponse = Unirest.post(callbackUrl + "/Authorization")
                .header("Content-Type", "application/json")
                .body(authorizationRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("AuthorizationAPI RequestBody", authorizationRequestBody);
        Allure.addAttachment("AuthorizationAPI ResponseBody", authorizationResponse.getBody() + "  ResponseTime "+ (end - start) + " ms");
        return authorizationResponse;
    }


    public HttpResponse<String> getBalanceAPI(String AuthorizationToken, int ProductID) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_03_apiVariables_getBalance_request.setToken(AuthorizationToken);
        iqSoft_03_apiVariables_getBalance_request.setProductId(ProductID);
        String getBalanceRequestBody = gson.toJson(iqSoft_03_apiVariables_getBalance_request);
        logger.info("GetBalanceRequestBody : " + getBalanceRequestBody);

        long start = System.currentTimeMillis();
        HttpResponse<String> getBalanceResponse = Unirest.post(callbackUrl + "/GetBalance")
                .header("Content-Type", "application/json")
                .body(getBalanceRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("GetBalanceAPI RequestBody", getBalanceRequestBody);
        Allure.addAttachment("GetBalanceAPI ResponseBody", getBalanceResponse.getBody() + "  ResponseTime "+ (end - start) + " ms");
        return getBalanceResponse;
    }


    public HttpResponse<String> creditAPI(String AuthorizationToken, String CurrencyID, int GameID, int OperationTypeId,
                                          String TransactionId, double Amount, int BetState) throws UnirestException {

        // OperationTypeId  3_Bet, 4_Win, 15_BetRollBack,  17_WinRollBack
        // BetStates  2_Won, 3_Lost, 4_Returned
        // BetTypes  1_Single, 2_Multiple, 3_System

        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_04_apiVariables_credit_request.setToken(AuthorizationToken);
        iqSoft_04_apiVariables_credit_request.setCurrencyId(CurrencyID);
        iqSoft_04_apiVariables_credit_request.setProductId(GameID);
        iqSoft_04_apiVariables_credit_request.setOperationTypeId(OperationTypeId);
        iqSoft_04_apiVariables_credit_request.setTransactionId(TransactionId);
        iqSoft_04_apiVariables_credit_request.setAmount(Amount);
        iqSoft_04_apiVariables_credit_request.setBetState(BetState);

        String CreditRequestBody = gson.toJson(iqSoft_04_apiVariables_credit_request);
        logger.info("CreditRequestBody : " + CreditRequestBody);

        long start = System.currentTimeMillis();
        HttpResponse<String> creditResponse = Unirest.post(callbackUrl + "/Credit")
                .header("Content-Type", "application/json")
                .body(CreditRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("CreditAPI RequestBody", CreditRequestBody);
        Allure.addAttachment("CreditAPI ResponseBody", creditResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        return creditResponse;
    }

    public HttpResponse<String> debitAPI(String AuthorizationToken, int ProductID, double Amount, String TransactionId,
                                         String CreditTransactionId, String Currency) throws UnirestException {  //if type = 1 one time else IDArrayList size
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_05_apiVariables_debit_request.setTransactionId(TransactionId);
        iqSoft_05_apiVariables_debit_request.setCreditTransactionId(CreditTransactionId);
        iqSoft_05_apiVariables_debit_request.setProductId(ProductID);
        iqSoft_05_apiVariables_debit_request.setAmount(Amount);
        iqSoft_05_apiVariables_debit_request.setCurrencyId(Currency);
        iqSoft_05_apiVariables_debit_request.setToken(AuthorizationToken);

        String DebitRequestBody = gson.toJson(iqSoft_05_apiVariables_debit_request);
        logger.info("DebitRequestBody : " + DebitRequestBody);

        long start = System.currentTimeMillis();
        HttpResponse<String> debitResponse = Unirest.post(callbackUrl + "/Debit")
                .header("Content-Type", "application/json")
                .body(DebitRequestBody)
                .asString();
        long end = System.currentTimeMillis();

        Allure.addAttachment("DebitAPI RequestBody", DebitRequestBody);
        Allure.addAttachment("DebitAPI ResponseBody", debitResponse.getBody() + "  ResponseTime "+ (end - start) + " ms");
        return debitResponse;
    }


    public HttpResponse<String> rollBackAPI(String AuthorizationToken, String UserName,int GameId, String RollbackTransactionId,
                                            String TransactionId, int OperationTypeId ) throws UnirestException {
        //if type = 1 one time else IDArrayList size
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_06_apiVariables_rollBack_request.setUserName(UserName);
        iqSoft_06_apiVariables_rollBack_request.setGameId(GameId);
        iqSoft_06_apiVariables_rollBack_request.setRollbackTransactionId(RollbackTransactionId);
        iqSoft_06_apiVariables_rollBack_request.setTransactionId(TransactionId);
        iqSoft_06_apiVariables_rollBack_request.setToken(AuthorizationToken);
        iqSoft_06_apiVariables_rollBack_request.setOperationTypeId(OperationTypeId);

        String RollBackRequestBody = gson.toJson(iqSoft_06_apiVariables_rollBack_request);
        logger.info("RollBackRequestBody : " + RollBackRequestBody);
        long start = System.currentTimeMillis();
        HttpResponse<String> rollBackResponse = Unirest.post(callbackUrl + "/Rollback")
                .header("Content-Type", "application/json")
                .body(RollBackRequestBody)
                .asString();
        long end = System.currentTimeMillis();

        Allure.addAttachment("RollBackAPI RequestBody", RollBackRequestBody);
        Allure.addAttachment("RollBackAPI ResponseBody", rollBackResponse.getBody() + "  ResponseTime "+ (end - start) + " ms");
        return rollBackResponse;
    }









    static FirefoxDriver driver;
    WebDriverWait webDriverWait;
    static String capturedToken;

    public String userToken = readConfig.getUserToken(); //If we need use token from config file
    static String timeOutCapturedToken;
    static String authorizationTimeOutToken;

    public org.openqa.selenium.html5.LocalStorage getLocalStorage() {
        WebStorage webStorage = (WebStorage) driver;
        return webStorage.getLocalStorage();
    }

    public void waitElementToBeVisible(WebElement element) {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getItem(String key) {
        return getLocalStorage().getItem(key);
    }

    public String captureUserToken() {

//        WebDriverManager.firefoxdriver().setup();
//        FirefoxOptions fOptions = new FirefoxOptions();
//        fOptions.addArguments("--headless", "--window-size=1920,1080");
//        fOptions.setHeadless(false);
//        driver = new FirefoxDriver();
//
//
//        driver.manage().window().maximize();
//        driver.get("https://ipls-staging.winsysgroup.com/home");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(50));
//
//        WebElement loginButtonMain = driver.findElement(By.xpath("//button[@class='button-style2-type-btn global_login-btn pointer']"));
//        waitElementToBeVisible(loginButtonMain);
//        loginButtonMain.click();
//
//        WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
//        waitElementToBeVisible(userName);
//        userName.sendKeys("g.babloyan@iqsoft.am");
//
//        WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='Password']"));
//        waitElementToBeVisible(password);
//        password.sendKeys("Test123456");
//
//        WebElement loginButtonPopUp = driver.findElement(By.xpath("//button[@class='craft_btn login_btn -btn']"));
//        waitElementToBeVisible(loginButtonPopUp);
//        loginButtonPopUp.click();
//
//        WebElement balanceSection = driver.findElement(By.xpath("//div[@class='balance_section']"));
//        waitElementToBeVisible(balanceSection);
//
//        String capturedToken = getItem("token");
//        logger.info("Captured User Token : " + capturedToken);
//        driver.quit();
//
//        //endregion
//
//        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  Test Suite was started ");
//        return capturedToken;
        return userToken;

    }


    @BeforeSuite
    public void setupSuite() throws InterruptedException {

        logger = Logger.getLogger("API");
        PropertyConfigurator.configure("log4j.properties");
        timeOutCapturedToken = captureUserToken()+1;
        capturedToken = captureUserToken();
    }

    @AfterSuite
    public void tearDownSuite() {
//        driver.quit();
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  Test Suite finished  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ");
        logger.info("");
        logger.info("");
        logger.info("");

    }


}




