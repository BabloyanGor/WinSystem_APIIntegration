package testCases;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import testData.*;
import utilities.ReadConfig;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;


public class BaseTest {
    public BaseTest() {
    }

    IqSoft_01_APIVariables_GetProductUrl_Request iqSoft02ApiVariables_getProductUrl_request = new IqSoft_01_APIVariables_GetProductUrl_Request();
    IqSoft_01_APIVariables_GetProductUrl_Response iqSoft01ApiVariables_getProductUrl_response = new IqSoft_01_APIVariables_GetProductUrl_Response();

    IqSoft_02_APISVariables_Authorization_Request iqSoft02ApisVariables_authorization_request = new IqSoft_02_APISVariables_Authorization_Request();
    IqSoft_02_APISVariables_Authorization_Response iqSoft_02_apisVariables_authorization_response = new IqSoft_02_APISVariables_Authorization_Response();

    IqSoft_03_APIVariables_GetBalance_Request iqSoft03ApiVariables_getBalance_request = new IqSoft_03_APIVariables_GetBalance_Request();
    IqSoft_03_APIVariables_GetBalance_Response iqSoft03ApiVariables_getBalance_response = new IqSoft_03_APIVariables_GetBalance_Response();

    IqSoft_04_APIVariables_Credit_Request iqSoft_04_apiVariables_credit_request = new IqSoft_04_APIVariables_Credit_Request();
    IqSoft_04_APIVariables_Credit_Response iqSoft_04_apiVariables_credit_response = new IqSoft_04_APIVariables_Credit_Response();

    IqSoft_05_APIVariables_Debit_Request iqSoft_05_apiVariables_debit_request = new IqSoft_05_APIVariables_Debit_Request();
    IqSoft_05_APIVariables_Debit_Response iqSoft_05_apiVariables_debit_response = new IqSoft_05_APIVariables_Debit_Response();
    public static Logger logger;
    ReadConfig readConfig = new ReadConfig();

    public String gameLaunchURL = readConfig.getGameLaunchURL();
    public String callbackUrl = readConfig.getCallbackUrl();


    //region <Variables for gameLaunchURL API>
    public int partnerID = readConfig.getPartnerID();
    public int productID = readConfig.getProductID();
    public int clientId = readConfig.getClientId();

    public String userToken = readConfig.getUserToken();
    //endregion


    //region <Variables for Authorization API>


    //endregion
//    public int partnerID = readConfig.getPartnerID();
//    public String callbackUrl = readConfig.getCallbackUrl();
//    public String productID = readConfig.getProductID();
//    public String sessionToken = readConfig.getSessionToken();
//    public String currencyId = readConfig.getCurrencyId();
//    public String languageId = readConfig.getLanguageId();


    /////////////////////////////////////////////////////////////


    public HttpResponse<String> getUrlAPI() throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft02ApiVariables_getProductUrl_request.setPartnerId(partnerID);
        iqSoft02ApiVariables_getProductUrl_request.setProductId(productID);
        iqSoft02ApiVariables_getProductUrl_request.setClientId(clientId);
        iqSoft02ApiVariables_getProductUrl_request.setUserToken(capturedToken);  //use var <userToken> if token was passed from config
        String getURLRequestBody = gson.toJson(iqSoft02ApiVariables_getProductUrl_request);
        logger.info("getURLRequestBody :" + getURLRequestBody);
        HttpResponse<String> response = Unirest.post(gameLaunchURL)
                .header("Content-Type", "application/json")
                .body(getURLRequestBody)
                .asString();
        return response;
    }


    public HttpResponse<String> authorizationAPI() throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft02ApisVariables_authorization_request.setToken(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken());
//        iqSoft02ApisVariables_authorization_request.setPartnerId(partnerID);
        String authorizationRequestBody = gson.toJson(iqSoft02ApisVariables_authorization_request);
        logger.info("AuthorizationRequestBody : " + authorizationRequestBody);

        HttpResponse<String> response = Unirest.post(callbackUrl + "/Authorization")
                .header("Content-Type", "application/json")
                .body(authorizationRequestBody)
                .asString();
        return response;
    }


    public HttpResponse<String> getBalanceAPI() throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft03ApiVariables_getBalance_request.setToken(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken());
//        iqSoft02ApisVariables_authorization_request.setPartnerId(partnerID);
        String getBalanceRequestBody = gson.toJson(iqSoft03ApiVariables_getBalance_request);
        logger.info("GetBalanceRequestBody : " + getBalanceRequestBody);

        HttpResponse<String> response = Unirest.post(callbackUrl + "/GetBalance")
                .header("Content-Type", "application/json")
                .body(getBalanceRequestBody)
                .asString();
        return response;
    }





    static ArrayList<String> IDArrayList = new ArrayList<>();
    static String ID = "QA_Test-"+ RandomStringUtils.randomNumeric(10);;
    public ArrayList<String> generateRandomIDArray(int num) {
        String randomNumber;
        for (int i=0;i<num;i++){
            randomNumber ="QA_Test-"+ RandomStringUtils.randomNumeric(10);
            IDArrayList.add(randomNumber);
        }
        return IDArrayList;
    }

    public HttpResponse<String> creditAPIOneTime() throws UnirestException {
            Gson gson = new Gson();
            Unirest.setTimeouts(0, 0);
            iqSoft_04_apiVariables_credit_request.setToken(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken());
            iqSoft_04_apiVariables_credit_request.setRoundId(ID);
            iqSoft_04_apiVariables_credit_request.setTransactionId(ID);
            String CreditRequestBody = gson.toJson(iqSoft_04_apiVariables_credit_request);
            logger.info("CreditRequestBody : " + CreditRequestBody);
            HttpResponse<String> response = Unirest.post(callbackUrl + "/Credit")
                    .header("Content-Type", "application/json")
                    .body(CreditRequestBody)
                    .asString();
        return response;
    }


    public HttpResponse<String> creditAPINumTimes(int num) throws UnirestException {
        generateRandomIDArray(num);
        HttpResponse<String> response = null;
            for (String randomID : IDArrayList){
            Gson gson = new Gson();
            Unirest.setTimeouts(0, 0);
            iqSoft_04_apiVariables_credit_request.setToken(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken());
            iqSoft_04_apiVariables_credit_request.setRoundId(randomID);
            iqSoft_04_apiVariables_credit_request.setTransactionId(randomID);
            String CreditRequestBody = gson.toJson(iqSoft_04_apiVariables_credit_request);
            logger.info("CreditRequestBody : " + CreditRequestBody);
            response = Unirest.post(callbackUrl + "/Credit")
                    .header("Content-Type", "application/json")
                    .body(CreditRequestBody)
                    .asString();
        }
        return response;
    }







    public HttpResponse<String> debitAPIOneTime() throws UnirestException {  //if type = 1 one time else IDArrayList size
            Gson gson = new Gson();
            Unirest.setTimeouts(0, 0);
            iqSoft_05_apiVariables_debit_request.setToken(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken());
            iqSoft_05_apiVariables_debit_request.setRoundId(ID);
            iqSoft_05_apiVariables_debit_request.setTransactionId(ID);
            iqSoft_05_apiVariables_debit_request.setCreditTransactionId(ID);
            String DebitRequestBody = gson.toJson(iqSoft_05_apiVariables_debit_request);
            logger.info("DebitRequestBody : " + DebitRequestBody);
            HttpResponse<String> response = Unirest.post(callbackUrl + "/Debit")
                    .header("Content-Type", "application/json")
                    .body(DebitRequestBody)
                    .asString();
        return response;
    }


    public HttpResponse<String> debitAPINumTimes() throws UnirestException {  //if type = 1 one time else IDArrayList size
        HttpResponse<String> response = null;
            for (String randomID : IDArrayList){
                Gson gson = new Gson();
                Unirest.setTimeouts(0, 0);
                iqSoft_05_apiVariables_debit_request.setToken(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken());
                iqSoft_05_apiVariables_debit_request.setRoundId(randomID);
                iqSoft_05_apiVariables_debit_request.setTransactionId(randomID);
                String DebitRequestBody = gson.toJson(iqSoft_05_apiVariables_debit_request);
                logger.info("DebitRequestBody : " + DebitRequestBody);
                response = Unirest.post(callbackUrl + "/Debit")
                        .header("Content-Type", "application/json")
                        .body(DebitRequestBody)
                        .asString();
            }
        return response;
    }



    static ChromeDriver driver;
    WebDriverWait webDriverWait;
    String capturedToken;

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

    @BeforeSuite
    public void setupSuite() throws InterruptedException {
        logger = Logger.getLogger("API");
        PropertyConfigurator.configure("Log4j.properties");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  Test Suite was started ");

        WebDriverManager.chromedriver().setup();
            ChromeOptions cOptions = new ChromeOptions();
            cOptions.addArguments("--headless", "--window-size=1920,1080");
            cOptions.setHeadless(true);
            driver = new ChromeDriver(cOptions);

//        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://ipls-staging.winsysgroup.com/home");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(50));

        WebElement loginButtonMain = driver.findElement(By.xpath("//button[@class='button-style2-type-btn global_login-btn pointer']"));
        waitElementToBeVisible(loginButtonMain);
        loginButtonMain.click();

        WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
        waitElementToBeVisible(userName);
        userName.sendKeys("g.babloyan@iqsoft.am");

        WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='Password']"));
        waitElementToBeVisible(password);
        password.sendKeys("Test123456");

        WebElement loginButtonPopUp =driver.findElement(By.xpath("//button[@class='craft_btn login_btn -btn']"));
        waitElementToBeVisible(loginButtonPopUp);
        loginButtonPopUp.click();

        WebElement balanceSection =driver.findElement(By.xpath("//div[@class='balance_section']"));
        waitElementToBeVisible(balanceSection);

        capturedToken = getItem("token");
        logger.info("Captured Token : " + capturedToken);


    }

    @AfterSuite
    public void tearDownSuite() {
        driver.quit();
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  Test Suite finished  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ");
    }


    public void writeInExel(ArrayList<String> errorSrcXl, String src, String shitName) throws IOException {
        String target = System.getProperty("user.dir") + src;
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream file = new FileOutputStream(target);
        XSSFSheet sheet = workbook.createSheet(shitName);
        sheet.setColumnWidth(0, 20000);
        int l = 0;
        for (String err : errorSrcXl) {
            XSSFRow row = sheet.createRow(l);
            row.createCell(0).setCellValue(err);
            l++;
        }
        workbook.write(file);
        workbook.close();
    }

}




