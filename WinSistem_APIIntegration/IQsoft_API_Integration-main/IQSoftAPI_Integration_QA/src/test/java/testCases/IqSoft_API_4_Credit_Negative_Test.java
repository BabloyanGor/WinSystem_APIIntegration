package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;

public class IqSoft_API_4_Credit_Negative_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;
    int amount = 1;

    @Test(priority = 1)
    @Description("Verify Credit API_s response with Expired Token")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();
        HttpResponse<String> response = creditAPI(authorizationTimeOutToken, currency,clientProductID,
                3, creditValidTransactionID, betAmountCredit,1);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_04_apiVariables_credit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_04_apiVariables_credit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 29);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "SessionExpired", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }

    @Test(priority = 2)
    @Description("Verify Credit API_s response with invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();
        HttpResponse<String> response = creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken() + "1", currency,clientProductID,
                3, creditValidTransactionID, betAmountCredit,1);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_04_apiVariables_credit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_04_apiVariables_credit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 28);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "SessionNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }

    @Test(priority = 3)
    @Description("Verify Credit API_s response with invalid ProductID")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithInvalidProductID() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();
        HttpResponse<String> response = creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,-10,
                3, creditValidTransactionID, betAmountCredit,1);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_04_apiVariables_credit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_04_apiVariables_credit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 43);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "ProductNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }


    @Test(priority = 4)
    @Description("Verify Credit API_s response  using same TransactionID twice")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseUsingTransactionIDTwice() throws UnirestException, IOException {
        String TransactionIDCopy = randomCreditTransactionID();
        SoftAssert softAssert = new SoftAssert();
        creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, TransactionIDCopy, betAmountCredit,1);
        Unirest.shutdown();

        HttpResponse<String> response = creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, TransactionIDCopy, betAmountCredit,1);
        Unirest.shutdown();

        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_04_apiVariables_credit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_04_apiVariables_credit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 69);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "ClientDocumentAlreadyExists", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }



    @Test(priority = 5)
    @Description("Verify Credit API_s response  using Amount Higher Then Balance")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseUsingAmountHigherThenBalance() throws UnirestException, IOException {
        String TransactionID =randomCreditTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalance = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalance.getBody());
        double beforeCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());

        HttpResponse<String> response = creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, TransactionID, beforeCreditBalance+10,1);
        Unirest.shutdown();

        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_04_apiVariables_credit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_04_apiVariables_credit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 71);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "LowBalance", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseObject(), "null");

        softAssert.assertAll();
    }

//    public static ArrayList<String> getTransactionIdArrayList = new ArrayList<>();
    @Test(priority = 6, dataProvider = "invalidAmountData")
    @Description("Verify Credit API_s response with invalid Amount")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithInvalidAmount(String errAmount) throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceAPIBefore = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        Unirest.shutdown();
        statusCod = responseGetBalanceAPIBefore.getStatus();
        jsonObjectBody = new JSONObject(responseGetBalanceAPIBefore.getBody());
        iqSoft_03_apiVariables_getBalance_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        double balanceBeforeCredit = iqSoft_03_apiVariables_getBalance_response.getBalance();
        logger.info("GetBalance API Response balanceBefore : " + iqSoft_03_apiVariables_getBalance_response.getBalance());

        String randomTransactionID = randomCreditTransactionID();
        double errorAmount = Double.parseDouble(errAmount);
        HttpResponse<String> responseCreditAPI = creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, randomTransactionID, errorAmount,1);

        Unirest.shutdown();
        statusCod = responseCreditAPI.getStatus();
        jsonObjectBody = new JSONObject(responseCreditAPI.getBody());

        iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_04_apiVariables_credit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());

//        getTransactionIdArrayList.add(randomID());

        iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_04_apiVariables_credit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));


        HttpResponse<String> responseGetBalanceAPIAfter = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        Unirest.shutdown();
        statusCod = responseGetBalanceAPIAfter.getStatus();
        jsonObjectBody = new JSONObject(responseGetBalanceAPIAfter.getBody());
        iqSoft_03_apiVariables_getBalance_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        double balanceAfterCredit = iqSoft_03_apiVariables_getBalance_response.getBalance();
        logger.info("GetBalance API Response balanceAfter : " + iqSoft_03_apiVariables_getBalance_response.getBalance());


        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getClientId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), "null");
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 39);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "WrongOperationAmount", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseObject(), "null");
        softAssert.assertEquals(balanceBeforeCredit, balanceAfterCredit);
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidAmountData")
    Object[][] AmountInvalidData() throws IOException {
        String[][] arr = {
                {"0"},
                {"-1"},
                {"-1.5 "},
                {"-1.05"},
                {"-1.45"}

        };
        return arr;
    }


}
