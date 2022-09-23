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
import testData.APIVariables_GetBalance_Response;
import testData.APIVariables_GetProductUrl_Response;

import java.io.IOException;

public class API_3_GetBalance  extends BaseTest{
    APIVariables_GetBalance_Response apiVariables_getBalance_response = new APIVariables_GetBalance_Response();
    JSONObject jsonObjectBody;
    int statusCod;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> response = getBalanceAPI();
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        apiVariables_getBalance_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("GetBalance API Response ClientId : " + apiVariables_getBalance_response.getClientId());

        apiVariables_getBalance_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("GetBalance API Response CurrencyId : " + apiVariables_getBalance_response.getCurrencyId());

        apiVariables_getBalance_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("GetBalance API Response Balance : " + apiVariables_getBalance_response.getBalance());

        apiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("GetBalance API Response ResponseCode : " + apiVariables_getBalance_response.getResponseCode());

        apiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("GetBalance API Response Description : " + apiVariables_getBalance_response.getDescription());

        apiVariables_getBalance_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("GetBalance API Response ResponseObject : " + apiVariables_getBalance_response.getResponseObject());

    }


    @Test(priority = 1)
    @Description("Verify GetBalance API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void validateStatusCodGetBalanceAPI() {
        logger.info("GetBalance API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200,statusCod);
    }



    @Test(priority = 2,dependsOnMethods = { "validateStatusCodGetBalanceAPI" })
    @Description("Verify GetBalance API_s Response ClientID != null")
    public void GetBalanceValidateClientIDISNotNull() {
        Assert.assertNotEquals(apiVariables_getBalance_response.getClientId(),null);
    }

    @Test(priority = 3,dependsOnMethods = { "validateStatusCodGetBalanceAPI" })
    @Description("Verify GetBalance API_s Response CurrencyId = Request CurrencyId ")
    public void GetBalanceValidateCurrencyIDEqualRequestCurrencyIDAndNotNull() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(apiVariables_getBalance_response.getCurrencyId(),null);
        softAssert.assertNotEquals(apiVariables_getBalance_response.getCurrencyId(),apisVariables_authorization_request.getCurrencyId());
    }

    @Test(priority = 4,dependsOnMethods = { "validateStatusCodGetBalanceAPI" })
    @Description("Verify GetBalance API_s Response Balance != null")
    public void GetBalanceValidateBalanceNotNull() {
        Assert.assertNotEquals(apiVariables_getBalance_response.getClientId(),null);
    }


    @Test(priority = 5, dependsOnMethods = {"validateStatusCodGetBalanceAPI"})
    @Description("Verify GetBalance API_s Response ResponseCode = 0")
    public void GetBalanceValidateResponseCodEqualsZero() {
        Assert.assertEquals(apiVariables_getBalance_response.getResponseCode(), 0);
    }


    @Test(priority = 6, dependsOnMethods = {"validateStatusCodGetBalanceAPI"})
    @Description("Verify GetBalance API_s Response Description = null")
    public void GetBalanceValidateDescriptionEqualsNull() {
        Assert.assertEquals(apiVariables_getBalance_response.getDescription(), "null");
    }


    @Test(priority = 7, dependsOnMethods = {"validateStatusCodGetBalanceAPI"})
    @Description("Verify GetBalance API_s Response ResponseObject = null")
    public void GetBalanceValidateResponseObjectEqualsNull() {
        Assert.assertEquals( apiVariables_getBalance_response.getResponseObject(),"null");
    }


















}
