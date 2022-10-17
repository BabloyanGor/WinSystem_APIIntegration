package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class IqSoft_API_3_GetBalance_Negative_Test extends BaseTest{
    JSONObject jsonObjectBody;
    int statusCod;

    @Test(priority = 1)
    @Description("Verify GetBalance API_s response with Expired Token")
    @Severity(SeverityLevel.BLOCKER)
    public void GetBalanceAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {
        HttpResponse<String> response = getBalanceAPI(authorizationTimeOutToken,clientProductID);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_03_apiVariables_getBalance_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_03_apiVariables_getBalance_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_03_apiVariables_getBalance_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_03_apiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_03_apiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_03_apiVariables_getBalance_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getClientId(),"null");
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getCurrencyId(),"null");
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getBalance(),0.0);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseCode(),29);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getDescription(),"SessionExpired", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseObject(),"null");

        softAssert.assertAll();
    }

    @Test(priority = 2)
    @Description("Verify GetBalance API_s response with invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void GetBalanceAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {
        HttpResponse<String> response = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken() + "1",clientProductID);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_03_apiVariables_getBalance_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_03_apiVariables_getBalance_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_03_apiVariables_getBalance_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_03_apiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_03_apiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_03_apiVariables_getBalance_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getClientId(),"null");
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getCurrencyId(),"null");
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getBalance(),0.0);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseCode(),28);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getDescription(),"SessionNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseObject(),"null");

        softAssert.assertAll();
    }



    @Test(priority = 3)
    @Description("Verify GetBalance API_s response with invalid ProductID")
    @Severity(SeverityLevel.BLOCKER)
    public void GetBalanceAPIValidateResponseWithInvalidProductID() throws UnirestException, IOException {
        HttpResponse<String> response = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken() ,-10);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_03_apiVariables_getBalance_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_03_apiVariables_getBalance_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_03_apiVariables_getBalance_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_03_apiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_03_apiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_03_apiVariables_getBalance_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getClientId(),"null");
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getCurrencyId(),"null");
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getBalance(),0.0);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseCode(),43);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getDescription(),"ProductNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseObject(),"null");

        softAssert.assertAll();
    }
}
