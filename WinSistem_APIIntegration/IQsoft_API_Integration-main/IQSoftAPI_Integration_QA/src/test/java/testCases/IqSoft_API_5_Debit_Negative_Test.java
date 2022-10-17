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

public class IqSoft_API_5_Debit_Negative_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;

    @Test(priority = 1)
    @Description("Verify Debit API_s response with Invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double beforeCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();


        creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, creditTransactionID, betAmountCredit,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double afterCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseDebitAPI = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken() + "1", clientProductID, betAmountDebit,
                randomDebitTransactionID(),creditTransactionID,currency);
        statusCod = responseDebitAPI.getStatus();
        jsonObjectBody = new JSONObject(responseDebitAPI.getBody());
        Unirest.shutdown();
        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double afterDebitBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();


        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBalance(), 0.0,"5");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 28,"6");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "SessionNotFound", "Error Description: "
                + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null","7");

        softAssert.assertEquals(beforeCreditBalance, afterCreditBalance+betAmountCredit);
        softAssert.assertEquals(afterCreditBalance, afterDebitBalance);
        softAssert.assertAll();
    }


    @Test(priority = 2)
    @Description("Verify Debit API_s response with Invalid ProductID")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidProductID() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double beforeCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();


        creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, creditTransactionID, betAmountCredit,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double afterCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseDebitAPI = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), -10, betAmountDebit,
                randomDebitTransactionID(),creditTransactionID,currency);
        statusCod = responseDebitAPI.getStatus();
        jsonObjectBody = new JSONObject(responseDebitAPI.getBody());
        Unirest.shutdown();
        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double afterDebitBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();


        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 43);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "ProductNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");

        softAssert.assertEquals(beforeCreditBalance, afterCreditBalance+betAmountCredit);
        softAssert.assertEquals(afterCreditBalance, afterDebitBalance);
        softAssert.assertAll();
    }


    @Test(priority = 3)
    @Description("Verify Debit API_s response using same TransactionID twice")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingTransactionIDTwice() throws UnirestException, IOException {

        String creditTransactionID1 = randomCreditTransactionID();
        String creditTransactionID2 = randomCreditTransactionID();
        String debitTransactionIDCopy = randomCreditTransactionID();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double beforeCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();


        creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, creditTransactionID1, betAmountCredit,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit1 = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit1.getBody());
        double afterCreditBalance1 = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();

        creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, creditTransactionID2, betAmountCredit,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit2 = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit2.getBody());
        double afterCreditBalance2 = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseDebitAPI1 = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, betAmountDebit,
                debitTransactionIDCopy,creditTransactionID1,currency);
        jsonObjectBody = new JSONObject(responseDebitAPI1.getBody());
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterDebit1 = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit1.getBody());

        double afterDebitBalance1 = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseDebitAPI = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, betAmountDebit,
                debitTransactionIDCopy,creditTransactionID2,currency);
        jsonObjectBody = new JSONObject(responseDebitAPI.getBody());
        statusCod = responseDebitAPI.getStatus();
        Unirest.shutdown();

        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        HttpResponse<String> responseGetBalanceAfterDebit2 = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit2.getBody());
        double afterDebitBalance2 = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();


        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBalance(), 0.0,"1");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 69);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "ClientDocumentAlreadyExists",
                "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null","2");
        softAssert.assertEquals(beforeCreditBalance, afterCreditBalance1+betAmountCredit,"3");
        softAssert.assertEquals(afterCreditBalance1, afterCreditBalance2+betAmountCredit,"4");
        softAssert.assertEquals(afterCreditBalance2, afterDebitBalance1-betAmountDebit,"5");
        softAssert.assertEquals(afterDebitBalance1, afterDebitBalance2,"6");
        softAssert.assertAll();
    }

//    @Test(priority = 4)
//    @Description("Verify Debit API_s response using same CreditTransactionID twice")
//    @Severity(SeverityLevel.BLOCKER)
//    public void DebitAPIValidateResponseUsingCreditTransactionIDTwice() throws UnirestException, IOException {
//
//        String creditTransactionIDCopy = randomCreditTransactionID();
//        String debitTransactionID1 = randomCreditTransactionID();
//        String debitTransactionID2 = randomCreditTransactionID();
//
//        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
//        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
//        double beforeCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
//        Unirest.shutdown();
//
//
//        creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
//                3, creditTransactionIDCopy, betAmountCredit,1);
//        Unirest.shutdown();
//
//        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
//        double afterCreditBalance1 = Double.parseDouble(jsonObjectBody.get("Balance").toString());
//        Unirest.shutdown();
//
//
//        HttpResponse<String> responseDebitAPI1 = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, betAmountDebit,
//                debitTransactionID1,creditTransactionIDCopy,currency);
//        jsonObjectBody = new JSONObject(responseDebitAPI1.getBody());
//        Unirest.shutdown();
//
//        HttpResponse<String> responseGetBalanceAfterDebit1 = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit1.getBody());
//
//        double afterDebitBalance1 = Double.parseDouble(jsonObjectBody.get("Balance").toString());
//        Unirest.shutdown();
//
//        HttpResponse<String> responseDebitAPI = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, betAmountDebit,
//                debitTransactionID2,creditTransactionIDCopy,currency);
//        jsonObjectBody = new JSONObject(responseDebitAPI.getBody());
//        statusCod = responseDebitAPI.getStatus();
//        Unirest.shutdown();
//
//        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
//        logger.info("Debit API Response BetId : " + iqSoft_05_apiVariables_debit_response.getBetId());
//
//        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
//        logger.info("Debit API Response TransactionId : " + iqSoft_05_apiVariables_debit_response.getTransactionId());
//
//        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
//        logger.info("Debit API Response ClientId : " + iqSoft_05_apiVariables_debit_response.getClientId());
//
//        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
//        logger.info("Debit API Response CurrencyId : " + iqSoft_05_apiVariables_debit_response.getCurrencyId());
//
//        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
//        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());
//
//        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());
//
//        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
//        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());
//
//        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
//        logger.info("Debit API Response ResponseObject : " + iqSoft_05_apiVariables_debit_response.getResponseObject());
//
//        HttpResponse<String> responseGetBalanceAfterDebit2 = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit2.getBody());
//        double afterDebitBalance2 = Double.parseDouble(jsonObjectBody.get("Balance").toString());
//        Unirest.shutdown();
//
//
//        SoftAssert softAssert = new SoftAssert();
//
//        softAssert.assertEquals(statusCod, 200);
//
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBalance(), 0.0,"1");
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 50);
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "CanNotConnectCreditAndDebit",
//                "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null","2");
//        softAssert.assertEquals(beforeCreditBalance, afterCreditBalance1+betAmountCredit,"3");
//        softAssert.assertEquals(afterCreditBalance1, afterDebitBalance1-betAmountDebit,"5");
//        softAssert.assertEquals(afterDebitBalance1, afterDebitBalance2,"6");
//        softAssert.assertAll();
//    }


    @Test(priority = 5, dataProvider = "invalidAmount")
    @Description("Verify Debit API_s response using Invalid Amount")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidAmount(Double errorAmount) throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double beforeCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();


        creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, creditTransactionID, betAmountCredit,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double afterCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseDebitAPI = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, errorAmount,
                randomDebitTransactionID(),creditTransactionID,currency);
        jsonObjectBody = new JSONObject(responseDebitAPI.getBody());
        statusCod = responseDebitAPI.getStatus();
        Unirest.shutdown();
        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double afterDebitBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();


        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getBalance(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 39);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "WrongOperationAmount", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");

        softAssert.assertEquals(beforeCreditBalance, afterCreditBalance+betAmountCredit);
        softAssert.assertEquals(afterCreditBalance, afterDebitBalance);
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidAmount")
    Object[][] invalidAmount() {
        Double[][] arr = {
                {-1.0},
                {-1.5},
                {-1.05},
                {-1.005}
        };
        return arr;
    }



    @Test(priority = 6)
    @Description("Verify Debit API_s response using Invalid CreditTransactionID")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidCreditTransactionID() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double beforeDebitBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseDebitAPI = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, betAmountDebit,
                randomDebitTransactionID(),creditTransactionID,currency);
        statusCod = responseDebitAPI.getStatus();
        jsonObjectBody = new JSONObject(responseDebitAPI.getBody());
        Unirest.shutdown();
        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double afterDebitBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Unirest.shutdown();

        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getBalance(), 0.0);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 50);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "CanNotConnectCreditAndDebit", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");
        softAssert.assertEquals(beforeDebitBalance, afterDebitBalance);
        softAssert.assertAll();
    }

}
