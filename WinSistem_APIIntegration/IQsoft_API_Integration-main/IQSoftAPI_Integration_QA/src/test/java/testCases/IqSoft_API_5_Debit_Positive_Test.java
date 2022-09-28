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

public class IqSoft_API_5_Debit_Positive_Test extends BaseTest{

    JSONObject jsonObjectBody;
    int statusCod;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> response = debitAPI();
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

    }


    @Test(priority = 1)
    @Description("Verify Credit API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateStatusCod() {
        logger.info("Debit API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Response ResponseCode = 0")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseCodEqualsZero() {
        Assert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0);
    }

    @Test(priority = 3, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Response Description = null")
    public void DebitAPIValidateDescriptionEqualsNull() {
        Assert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "null");
    }

    @Test(priority = 4, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Response ResponseObject = null")
    public void DebitAPIValidateResponseObjectEqualsNull() {
        Assert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");
    }


    @Test(priority = 5, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Response BetID != null")
    public void CreditAPIValidateBetIdNotNull() {
        Assert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getBetId(), "null");
    }

    @Test(priority = 6, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Response TransactionId != null")
    public void CreditAPIValidateTransactionIdNotNull() {
        Assert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getTransactionId(), "null");
    }

    @Test(priority = 7, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Response ClientID = 25")
    public void CreditAPIValidateClientID() {
        Assert.assertEquals(iqSoft_05_apiVariables_debit_response.getClientId(), String.valueOf(clientId));
    }

    @Test(priority = 8, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Response CurrencyId != null")
    public void DebitAPIValidateCurrencyIdNotNull() {
        Assert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId(), "null");
    }

    double balanceAfter = 0;
    double check = 0;
    int num = repeatNum;

    @Test(priority = 9, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Response Balance = Balance - BetAmount * num")
    public void CreditAPIValidateBalance() throws UnirestException {
        double balance = iqSoft_05_apiVariables_debit_response.getBalance();
        HttpResponse<String> responseSecond = debitAPINumTimes();
        jsonObjectBody = new JSONObject(responseSecond.getBody());
        balanceAfter = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        check =  balanceAfter - balance;
        Assert.assertEquals(check, (iqSoft_05_apiVariables_debit_request.getAmount())*num);
    }

    @Test(priority = 10, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify GetBalance API_s Balance after Debit")
    public void CreditAPIValidateBalanceAfterCreditGetBalance() throws UnirestException {
        HttpResponse<String> response = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(),4);
        jsonObjectBody = new JSONObject(response.getBody());
        double balanceAfterCredit = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Assert.assertEquals(balanceAfterCredit,balanceAfter);
    }

}
