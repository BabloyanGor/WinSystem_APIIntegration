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
import org.testng.annotations.Test;

import java.io.IOException;

public class IqSoft_API_4_Credit_Positive_Test extends BaseTest {
    JSONObject jsonObjectBody;
    double beforeCredit;
    int statusCod;
    int amount = 1;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> responseGetBalance = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalance.getBody());
        beforeCredit = Double.parseDouble(jsonObjectBody.get("Balance").toString());

        HttpResponse<String> response = creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, amount, ID, ID,currency);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectBody.get("BetId").toString());
        logger.info("Credit API Response BetId : " + iqSoft_04_apiVariables_credit_response.getBetId());

        iqSoft_04_apiVariables_credit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        logger.info("Credit API Response TransactionId : " + iqSoft_04_apiVariables_credit_response.getTransactionId());

        iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Credit API Response ClientId : " + iqSoft_04_apiVariables_credit_response.getClientId());

        iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Credit API Response CurrencyId : " + iqSoft_04_apiVariables_credit_response.getCurrencyId());

        iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Credit API Response Balance : " + iqSoft_04_apiVariables_credit_response.getBalance());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Credit API Response ResponseCode : " + iqSoft_04_apiVariables_credit_response.getResponseCode());

        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Credit API Response Description : " + iqSoft_04_apiVariables_credit_response.getDescription());

        iqSoft_04_apiVariables_credit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Credit API Response ResponseObject : " + iqSoft_04_apiVariables_credit_response.getResponseObject());

    }


    @Test(priority = 1)
    @Description("Verify Credit API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateStatusCod() {
        logger.info("Credit API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Response ResponseCode = 0")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseCodEqualsZero() {
        Assert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0);
    }

    @Test(priority = 3, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Response Description = null")
    public void CreditAPIValidateDescriptionEqualsNull() {
        Assert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "null");
    }

    @Test(priority = 4, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Response ResponseObject = null")
    public void CreditAPIValidateResponseObjectEqualsNull() {
        Assert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseObject(), "null");
    }

    @Test(priority = 5, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Response BetID != null")
    public void CreditAPIValidateBetIdNotNull() {
        Assert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null");
    }

    @Test(priority = 6, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Response TransactionId = null")
    public void CreditAPIValidateTransactionIdNotNull() {
        Assert.assertEquals(iqSoft_04_apiVariables_credit_response.getTransactionId(), "null");
    }

    @Test(priority = 7, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Response ClientID = 25")
    public void CreditAPIValidateClientID() {
        Assert.assertEquals(iqSoft_04_apiVariables_credit_response.getClientId(), String.valueOf(clientId));
    }

    @Test(priority = 8, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Response CurrencyId = Request currencyID")
    public void CreditAPIValidateCurrencyIdNotNull() {
        Assert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), currency);
    }

    @Test(priority = 9, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Balance After Debit")
    public void CreditAPIValidateBalanceAfterCredit() {
        double afterCredit = iqSoft_04_apiVariables_credit_response.getBalance();
        Assert.assertEquals(afterCredit , beforeCredit-iqSoft_04_apiVariables_credit_request.getAmount());
    }

    double balanceAfter = 0;
    double check = 0;
    int num = repeatNum;

    @Test(priority = 20, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Response Balance = Balance - BetAmount * num")
    public void CreditAPIValidateBalance() throws UnirestException {
        double balance = iqSoft_04_apiVariables_credit_response.getBalance();
        HttpResponse<String> responseSecond = creditAPINumTimes(num);
        jsonObjectBody = new JSONObject(responseSecond.getBody());
        balanceAfter = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        check = balance - balanceAfter;
        Assert.assertEquals(check, (iqSoft_04_apiVariables_credit_request.getAmount()) * num);
    }

    @Test(priority = 21, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify GetBalance API_s Balance after Credit")
    public void CreditAPIValidateBalanceAfterCreditGetBalance() throws UnirestException {
        HttpResponse<String> response = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(response.getBody());
        double balanceAfterCredit = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Assert.assertEquals(balanceAfterCredit, balanceAfter);
    }


}