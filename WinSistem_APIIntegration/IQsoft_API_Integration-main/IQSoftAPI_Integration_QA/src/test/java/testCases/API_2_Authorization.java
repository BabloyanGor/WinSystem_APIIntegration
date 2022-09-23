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
import org.testng.asserts.SoftAssert;
import testData.APISVariables_Authorization_Response;


import java.io.IOException;

public class API_2_Authorization extends BaseTest{

    APISVariables_Authorization_Response apisVariables_authorization_response = new APISVariables_Authorization_Response();
    JSONObject jsonObjectBody;
    int statusCod;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> response = authorizationAPI();
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        apisVariables_authorization_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Authorization API Response ClientId : " + apisVariables_authorization_response.getClientId());

        apisVariables_authorization_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Authorization API Response CurrencyId : " + apisVariables_authorization_response.getCurrencyId());

        apisVariables_authorization_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Authorization API Response Balance : " + apisVariables_authorization_response.getBalance());

        apisVariables_authorization_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Authorization API Response ResponseCode : " + apisVariables_authorization_response.getResponseCode());

        apisVariables_authorization_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Authorization API Response Description : " + apisVariables_authorization_response.getDescription());

        apisVariables_authorization_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Authorization API Response ResponseObject : " + apisVariables_authorization_response.getResponseObject());

    }


    @Test(priority = 1)
    @Description("Verify Authorization API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationValidateStatusCodAuthorizationAPI() {
        logger.info("Authorization API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200,statusCod);
    }

    @Test(priority = 2,dependsOnMethods = { "validateStatusCodAuthorizationAPI" })
    @Description("Verify Authorization API_s Response ClientID != null")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationValidateClientIDISNotNull() {
        Assert.assertNotEquals(apisVariables_authorization_response.getClientId(),null);
    }

    @Test(priority = 3,dependsOnMethods = { "validateStatusCodAuthorizationAPI" })
    @Description("Verify Authorization API_s Response CurrencyId = Request CurrencyId ")
    @Severity(SeverityLevel.NORMAL)
    public void AuthorizationValidateCurrencyIDEqualRequestCurrencyIDAndNotNull() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(apisVariables_authorization_response.getCurrencyId(),null);
        softAssert.assertNotEquals(apisVariables_authorization_response.getCurrencyId(),apisVariables_authorization_request.getCurrencyId());
    }

    @Test(priority = 4,dependsOnMethods = { "validateStatusCodAuthorizationAPI" })
    @Description("Verify Authorization API_s Response Balance != null")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationValidateBalanceNotNull() {
        Assert.assertNotEquals(apisVariables_authorization_response.getClientId(),null);
    }


    @Test(priority = 5, dependsOnMethods = {"validateStatusCodAuthorizationAPI"})
    @Description("Verify Authorization API_s Response ResponseCode = 0")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationValidateResponseCodEqualsZero() {
        Assert.assertEquals(apisVariables_authorization_response.getResponseCode(), 0);
    }


    @Test(priority = 6, dependsOnMethods = {"validateStatusCodAuthorizationAPI"})
    @Description("Verify Authorization API_s Response Description = null")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationValidateDescriptionEqualsNull() {
        Assert.assertEquals(apisVariables_authorization_response.getDescription(), "null");
    }


    @Test(priority = 7, dependsOnMethods = {"validateStatusCodAuthorizationAPI"})
    @Description("Verify Authorization API_s Response ResponseObject = null")
    @Severity(SeverityLevel.NORMAL)
    public void AuthorizationValidateResponseObjectEqualsNull() {
        Assert.assertEquals( apisVariables_authorization_response.getResponseObject(),"null");
    }







}
