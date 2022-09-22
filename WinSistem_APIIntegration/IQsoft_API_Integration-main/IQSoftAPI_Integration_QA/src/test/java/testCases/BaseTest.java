package testCases;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.testng.annotations.*;
import testData.APISVariables_Authorization_Request;
import testData.APIVariables_GetProductUrl_Request;
import utilities.ReadConfig;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class BaseTest {
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



    //Variables for Authorization API
//    public int partnerID = readConfig.getPartnerID();
//    public String callbackUrl = readConfig.getCallbackUrl();
//    public String productID = readConfig.getProductID();
//    public String sessionToken = readConfig.getSessionToken();
//    public String currencyId = readConfig.getCurrencyId();
//    public String languageId = readConfig.getLanguageId();



    public String getURLRequestBody ;
    public String getURLResponseBody;
    public String getURLHeaders;
    public String authorizationRequestBody ;
    public String authorizationResponseBody;
    /////////////////////////////////////////////////////////////



    public BaseTest() {
    }



    public HttpResponse<String> getUrlAPI() throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        APIVariables_GetProductUrl_Request apiVariables_getProductUrl_request = new APIVariables_GetProductUrl_Request();
        apiVariables_getProductUrl_request.setPartnerId(partnerID);
        apiVariables_getProductUrl_request.setProductId(productID);
        apiVariables_getProductUrl_request.setClientId(clientId);
        apiVariables_getProductUrl_request.setUserToken(userToken);
        getURLRequestBody  = gson.toJson(apiVariables_getProductUrl_request);

        HttpResponse<String> response = Unirest.post(gameLaunchURL)
                .header("Content-Type", "application/json")
                .body(getURLRequestBody )
                .asString();
        return response;
    }

//    public HttpResponse<String> authorizationAPI() throws UnirestException {
//        Gson gson = new Gson();
//        Unirest.setTimeouts(0, 0);
//        APISVariables_Authorization_Request apisVariables_authorization_request = new APISVariables_Authorization_Request();
//        API1_GetUrl api1_getUrl = new API1_GetUrl();
//        apisVariables_authorization_request.setToken(api1_getUrl.getAuthorizationToken());
//        apisVariables_authorization_request.setPartnerId(partnerID);
//        apisVariables_authorization_request.setProductId(productID);
//        authorizationRequestBody  = gson.toJson(apisVariables_authorization_request);
//
//        HttpResponse<String> response = Unirest.post(callbackUrl + "/Authorization")
//                .header("Content-Type", "application/json")
//                .body(authorizationRequestBody )
//                .asString();
//        return response;
//    }



    @BeforeSuite
    public void setup() throws InterruptedException {
        logger = Logger.getLogger("API");
        PropertyConfigurator.configure("Log4j.properties");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  Test Suite was started ");
    }

    @AfterSuite
    public void tearDown() {
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




