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

public class IqSoft_API_2_Authorization_Negative_Test extends  BaseTest{

    JSONObject jsonObjectBody;
    int statusCod;

    @Test(priority = 1)
    @Description("Verify Authorization API_s response with Expired Token")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = authorizationAPI(authorizationTimeOutToken, clientProductID);
        Unirest.shutdown();
        statusCod = response.getStatus();
        logger.info("Authorization API Response Status cod : " +statusCod);

        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_02_apisVariables_authorization_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_02_apisVariables_authorization_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_02_apisVariables_authorization_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_02_apisVariables_authorization_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_02_apisVariables_authorization_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_02_apisVariables_authorization_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getClientId(),"null");
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getCurrencyId(),"null");
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getBalance(),0.0);
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getResponseCode(),29);
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getDescription(),"SessionExpired", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getResponseObject(),"null");

        softAssert.assertAll();
    }
    @Test(priority = 2)
    @Description("Verify Authorization API_s response with invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = authorizationAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken() + "1", clientProductID);
        Unirest.shutdown();

        statusCod = response.getStatus();
        logger.info("Authorization API Response Status cod : " +statusCod);
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_02_apisVariables_authorization_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_02_apisVariables_authorization_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_02_apisVariables_authorization_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_02_apisVariables_authorization_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_02_apisVariables_authorization_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_02_apisVariables_authorization_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getClientId(),"null");
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getCurrencyId(),"null");
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getBalance(),0.0);
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getResponseCode(),28);
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getDescription(),"SessionNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getResponseObject(),"null");

        softAssert.assertAll();
    }

    @Test(priority =3)
    @Description("Verify Authorization API_s response with invalid ProductID")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateResponseWithInvalidProductID() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = authorizationAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), 1000);
        Unirest.shutdown();
        statusCod = response.getStatus();
        logger.info("Authorization API Response Status cod : " +statusCod);

        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_02_apisVariables_authorization_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_02_apisVariables_authorization_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_02_apisVariables_authorization_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        iqSoft_02_apisVariables_authorization_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_02_apisVariables_authorization_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_02_apisVariables_authorization_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getClientId(),"null");
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getCurrencyId(),"null");
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getBalance(),0.0);
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getResponseCode(),43);
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getDescription(),"ProductNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getResponseObject(),"null");

        softAssert.assertAll();
    }
}
