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
import testData.IqSoft_03_APIVariables_GetBalance_Response;
import testData.IqSoft_04_APIVariables_Credit_Response;

import java.io.IOException;

public class API_4_Credit extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> response = creditAPI();
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
    @Description("Verify GetBalance API_s Response Description = null")
    public void CreditAPIValidateDescriptionEqualsNull() {
        Assert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "null");
    }


    @Test(priority = 4, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify GetBalance API_s Response ResponseObject = null")
    public void CreditAPIValidateResponseObjectEqualsNull() {
        Assert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseObject(), "null");
    }







}