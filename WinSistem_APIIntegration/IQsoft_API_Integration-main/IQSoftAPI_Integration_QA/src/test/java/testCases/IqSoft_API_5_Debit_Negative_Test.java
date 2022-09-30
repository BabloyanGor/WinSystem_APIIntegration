package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;

public class IqSoft_API_5_Debit_Negative_Test extends BaseTest{
    JSONObject jsonObjectBody;
    int statusCod;
    int amount = 1;
    static ArrayList<String> IDForDebitArrayList = new ArrayList<>();

    @BeforeClass
    public void setUp() throws UnirestException, IOException {

        for(int i=0; i<10; i++){
            String TransactionID = randomID();
            HttpResponse<String> response = creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, amount, TransactionID, TransactionID,currency);
            IDForDebitArrayList.add(TransactionID);
            Unirest.shutdown();
        }

    }

    @Test(priority = 1)
    @Description("Verify Debit API_s response with Expired Token")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();
        HttpResponse<String> response = debitAPI(authorizationTimeOutToken, clientProductID, betAmount, IDForDebitArrayList.get(0), IDForDebitArrayList.get(0),currency);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        logger.info("Debit API Response BetId : " + iqSoft_05_apiVariables_debit_response.getBetId());

        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        logger.info("Debit API Response TransactionId : " + iqSoft_05_apiVariables_debit_response.getTransactionId());

        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Debit API Response ClientId : " + iqSoft_05_apiVariables_debit_response.getClientId());

        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Debit API Response CurrencyId : " + iqSoft_05_apiVariables_debit_response.getCurrencyId());

        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());

        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Debit API Response ResponseObject : " + iqSoft_05_apiVariables_debit_response.getResponseObject());


        softAssert.assertEquals(statusCod, 200);
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getBetId(), "null");
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getTransactionId(), "null");
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getClientId(), iqSoft_05_apiVariables_debit_request.getClientId());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId(), iqSoft_05_apiVariables_debit_request.getCurrencyId());
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getBalance(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "null", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }


    @Test(priority = 2)
    @Description("Verify Debit API_s response with Invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();
        HttpResponse<String> response = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken() + "1", clientProductID, betAmount, IDForDebitArrayList.get(1), IDForDebitArrayList.get(1),currency);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        logger.info("Debit API Response BetId : " + iqSoft_05_apiVariables_debit_response.getBetId());

        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        logger.info("Debit API Response TransactionId : " + iqSoft_05_apiVariables_debit_response.getTransactionId());

        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Debit API Response ClientId : " + iqSoft_05_apiVariables_debit_response.getClientId());

        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Debit API Response CurrencyId : " + iqSoft_05_apiVariables_debit_response.getCurrencyId());

        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());

        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Debit API Response ResponseObject : " + iqSoft_05_apiVariables_debit_response.getResponseObject());


        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 28);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "SessionNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }


    @Test(priority = 3)
    @Description("Verify Debit API_s response with Invalid ProductID")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidProductID() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();
        HttpResponse<String> response = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), 10000, betAmount, IDForDebitArrayList.get(2), IDForDebitArrayList.get(2),currency);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        logger.info("Debit API Response BetId : " + iqSoft_05_apiVariables_debit_response.getBetId());

        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        logger.info("Debit API Response TransactionId : " + iqSoft_05_apiVariables_debit_response.getTransactionId());

        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Debit API Response ClientId : " + iqSoft_05_apiVariables_debit_response.getClientId());

        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Debit API Response CurrencyId : " + iqSoft_05_apiVariables_debit_response.getCurrencyId());

        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());

        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Debit API Response ResponseObject : " + iqSoft_05_apiVariables_debit_response.getResponseObject());


        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 43);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "ProductNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }



















    @Test(priority = 4)
    @Description("Verify Debit API_s response using same TransactionID twice")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingTransactionIDTwice() throws UnirestException, IOException {

        HttpResponse<String> responseFirst = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, betAmount, IDForDebitArrayList.get(3), IDForDebitArrayList.get(3),currency);
        JSONObject jsonObjectBodyFirst = new JSONObject(responseFirst.getBody());
        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBodyFirst.get("Balance").toString()));
        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());
        double balanceFirst = iqSoft_05_apiVariables_debit_response.getBalance();
        Unirest.shutdown();


        HttpResponse<String> response = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, betAmount, IDForDebitArrayList.get(3), IDForDebitArrayList.get(3),currency);
        Unirest.shutdown();

        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        logger.info("Debit API Response BetId : " + iqSoft_05_apiVariables_debit_response.getBetId());

        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        logger.info("Debit API Response TransactionId : " + iqSoft_05_apiVariables_debit_response.getTransactionId());

        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Debit API Response ClientId : " + iqSoft_05_apiVariables_debit_response.getClientId());

        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Debit API Response CurrencyId : " + iqSoft_05_apiVariables_debit_response.getCurrencyId());

        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());
        double balanceSecond = iqSoft_05_apiVariables_debit_response.getBalance();

        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Debit API Response ResponseObject : " + iqSoft_05_apiVariables_debit_response.getResponseObject());


        Assert.assertEquals(balanceFirst,balanceSecond);
    }


    @Test(priority = 5)
    @Description("Verify Debit API_s response using Invalid Amount")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidAmount() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, -10, IDForDebitArrayList.get(4), IDForDebitArrayList.get(4),currency);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        logger.info("Debit API Response BetId : " + iqSoft_05_apiVariables_debit_response.getBetId());

        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        logger.info("Debit API Response TransactionId : " + iqSoft_05_apiVariables_debit_response.getTransactionId());

        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Debit API Response ClientId : " + iqSoft_05_apiVariables_debit_response.getClientId());

        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Debit API Response CurrencyId : " + iqSoft_05_apiVariables_debit_response.getCurrencyId());

        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());

        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Debit API Response ResponseObject : " + iqSoft_05_apiVariables_debit_response.getResponseObject());


        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 39);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "WrongOperationAmount", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }





    @Test(priority = 6,dataProvider = "invalidTransactionID")
    @Description("Verify Debit API_s response using Invalid Amount")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidCreditTransactionID(String errorTransactionID) throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalance = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalance.getBody());
        double beforeCredit = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        logger.info("Balance Was :" + beforeCredit);

        HttpResponse<String> response = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, betAmount, errorTransactionID, errorTransactionID,currency);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        logger.info("Debit API Response BetId : " + iqSoft_05_apiVariables_debit_response.getBetId());

        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        logger.info("Debit API Response TransactionId : " + iqSoft_05_apiVariables_debit_response.getTransactionId());

        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Debit API Response ClientId : " + iqSoft_05_apiVariables_debit_response.getClientId());

        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Debit API Response CurrencyId : " + iqSoft_05_apiVariables_debit_response.getCurrencyId());

        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());

        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Debit API Response ResponseObject : " + iqSoft_05_apiVariables_debit_response.getResponseObject());


        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 50);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "CanNotConnectCreditAndDebit", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }


    @DataProvider(name = "invalidTransactionID")
    Object[][] AmountInvalidData()  {
        String[][] arr = {
                {InvalidAmountTransactionID.get(0)},
                {InvalidAmountTransactionID.get(1)},
                {InvalidAmountTransactionID.get(2)},
                {InvalidAmountTransactionID.get(3)},
                {InvalidAmountTransactionID.get(4)}
        };
//        String[][] arr  = new String[InvalidAmountTransactionID.size()][0];
//        for(int i = 0; i< InvalidAmountTransactionID.size();i++){
//            String eeTransID = InvalidAmountTransactionID.get(i);
//            arr[i][0] = eeTransID;
//            System.out.println();
//        }
        return arr ;
    }
}
