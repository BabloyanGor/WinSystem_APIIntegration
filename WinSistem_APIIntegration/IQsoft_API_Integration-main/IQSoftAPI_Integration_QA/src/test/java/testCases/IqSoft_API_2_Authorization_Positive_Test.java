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


import java.io.IOException;

public class IqSoft_API_2_Authorization_Positive_Test extends BaseTest{

    JSONObject jsonObjectBody;
    int statusCod;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> response = authorizationAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_02_apisVariables_authorization_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Authorization API Response ClientId : " + iqSoft_02_apisVariables_authorization_response.getClientId());

        iqSoft_02_apisVariables_authorization_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Authorization API Response CurrencyId : " + iqSoft_02_apisVariables_authorization_response.getCurrencyId());

        iqSoft_02_apisVariables_authorization_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Authorization API Response Balance : " + iqSoft_02_apisVariables_authorization_response.getBalance());

        iqSoft_02_apisVariables_authorization_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Authorization API Response ResponseCode : " + iqSoft_02_apisVariables_authorization_response.getResponseCode());

        iqSoft_02_apisVariables_authorization_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Authorization API Response Description : " + iqSoft_02_apisVariables_authorization_response.getDescription());

        iqSoft_02_apisVariables_authorization_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Authorization API Response ResponseObject : " + iqSoft_02_apisVariables_authorization_response.getResponseObject());

    }


    @Test(priority = 1)
    @Description("Verify Authorization API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateStatusCod() {
        logger.info("Authorization API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200,statusCod);
    }

    @Test(priority = 2,dependsOnMethods = { "AuthorizationAPIValidateStatusCod" })
    @Description("Verify Authorization API_s Response ClientID != null")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateClientIDISNotNull() {
        Assert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getClientId(),null);
    }

    @Test(priority = 3,dependsOnMethods = { "AuthorizationAPIValidateStatusCod" })
    @Description("Verify Authorization API_s Response CurrencyId = Request CurrencyId ")
    @Severity(SeverityLevel.NORMAL)
    public void AuthorizationAPIValidateCurrencyIDEqualRequestCurrencyIDAndNotNull() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getCurrencyId(),null);
        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getCurrencyId(), iqSoft02ApisVariables_authorization_request.getCurrencyId());
    }

    @Test(priority = 4,dependsOnMethods = { "AuthorizationAPIValidateStatusCod" })
    @Description("Verify Authorization API_s Response Balance != null")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateBalanceNotNull() {
        Assert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getClientId(),null);
    }


    @Test(priority = 5, dependsOnMethods = {"AuthorizationAPIValidateStatusCod"})
    @Description("Verify Authorization API_s Response ResponseCode = 0")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateResponseCodEqualsZero() {
        Assert.assertEquals(iqSoft_02_apisVariables_authorization_response.getResponseCode(), 0);
    }


    @Test(priority = 6, dependsOnMethods = {"AuthorizationAPIValidateStatusCod"})
    @Description("Verify Authorization API_s Response Description = null")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateDescriptionEqualsNull() {
        Assert.assertEquals(iqSoft_02_apisVariables_authorization_response.getDescription(), "null");
    }


    @Test(priority = 7, dependsOnMethods = {"AuthorizationAPIValidateStatusCod"})
    @Description("Verify Authorization API_s Response ResponseObject = null")
    @Severity(SeverityLevel.NORMAL)
    public void AuthorizationAPIValidateResponseObjectEqualsNull() {
        Assert.assertEquals( iqSoft_02_apisVariables_authorization_response.getResponseObject(),"null");
    }







}
