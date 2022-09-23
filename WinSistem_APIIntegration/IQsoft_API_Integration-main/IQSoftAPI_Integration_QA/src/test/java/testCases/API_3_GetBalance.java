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
import testData.IqSoft_03_APIVariables_GetBalance_Response;

import java.io.IOException;

public class API_3_GetBalance  extends BaseTest{
    JSONObject jsonObjectBody;
    int statusCod;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> response = getBalanceAPI();
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft03ApiVariables_getBalance_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("GetBalance API Response ClientId : " + iqSoft03ApiVariables_getBalance_response.getClientId());

        iqSoft03ApiVariables_getBalance_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("GetBalance API Response CurrencyId : " + iqSoft03ApiVariables_getBalance_response.getCurrencyId());

        iqSoft03ApiVariables_getBalance_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("GetBalance API Response Balance : " + iqSoft03ApiVariables_getBalance_response.getBalance());

        iqSoft03ApiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("GetBalance API Response ResponseCode : " + iqSoft03ApiVariables_getBalance_response.getResponseCode());

        iqSoft03ApiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("GetBalance API Response Description : " + iqSoft03ApiVariables_getBalance_response.getDescription());

        iqSoft03ApiVariables_getBalance_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("GetBalance API Response ResponseObject : " + iqSoft03ApiVariables_getBalance_response.getResponseObject());

    }


    @Test(priority = 1)
    @Description("Verify GetBalance API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void GetBalanceAPIValidateStatusCod() {
        logger.info("GetBalance API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200,statusCod);
    }



    @Test(priority = 2,dependsOnMethods = { "GetBalanceAPIValidateStatusCod" })
    @Description("Verify GetBalance API_s Response ClientID != null")
    public void GetBalanceAPIValidateClientIDISNotNull() {
        Assert.assertNotEquals(iqSoft03ApiVariables_getBalance_response.getClientId(),null);
    }

    @Test(priority = 3,dependsOnMethods = { "GetBalanceAPIValidateStatusCod" })
    @Description("Verify GetBalance API_s Response CurrencyId = Request CurrencyId ")
    public void GetBalanceAPIValidateCurrencyIDEqualRequestCurrencyIDAndNotNull() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(iqSoft03ApiVariables_getBalance_response.getCurrencyId(),null);
        softAssert.assertNotEquals(iqSoft03ApiVariables_getBalance_response.getCurrencyId(), iqSoft02ApisVariables_authorization_request.getCurrencyId());
    }

    @Test(priority = 4,dependsOnMethods = { "GetBalanceAPIValidateStatusCod" })
    @Description("Verify GetBalance API_s Response Balance != null")
    public void GetBalanceAPIValidateBalanceNotNull() {
        Assert.assertNotEquals(iqSoft03ApiVariables_getBalance_response.getClientId(),null);
    }


    @Test(priority = 5, dependsOnMethods = {"GetBalanceAPIValidateStatusCod"})
    @Description("Verify GetBalance API_s Response ResponseCode = 0")
    public void GetBalanceAPIValidateResponseCodEqualsZero() {
        Assert.assertEquals(iqSoft03ApiVariables_getBalance_response.getResponseCode(), 0);
    }


    @Test(priority = 6, dependsOnMethods = {"GetBalanceAPIValidateStatusCod"})
    @Description("Verify GetBalance API_s Response Description = null")
    public void GetBalanceAPIValidateDescriptionEqualsNull() {
        Assert.assertEquals(iqSoft03ApiVariables_getBalance_response.getDescription(), "null");
    }


    @Test(priority = 7, dependsOnMethods = {"GetBalanceAPIValidateStatusCod"})
    @Description("Verify GetBalance API_s Response ResponseObject = null")
    public void GetBalanceAPIValidateResponseObjectEqualsNull() {
        Assert.assertEquals( iqSoft03ApiVariables_getBalance_response.getResponseObject(),"null");
    }


















}
