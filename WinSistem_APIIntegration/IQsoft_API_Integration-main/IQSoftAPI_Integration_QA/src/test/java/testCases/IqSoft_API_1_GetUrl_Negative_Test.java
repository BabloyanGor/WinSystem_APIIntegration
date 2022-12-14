package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class IqSoft_API_1_GetUrl_Negative_Test extends BaseTest {

    JSONObject jsonObjectBody;
    int statusCod;

    @Test(priority = 1)
    @Description("Verify getURL API_s response with invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void GetUrlAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetUrlAPI = getUrlAPI(partnerID,productID,clientId,timeOutCapturedToken);
        Unirest.shutdown();
        statusCod = responseGetUrlAPI.getStatus();
        jsonObjectBody = new JSONObject(responseGetUrlAPI.getBody());

        iqSoft01ApiVariables_getProductUrl_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft01ApiVariables_getProductUrl_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft01ApiVariables_getProductUrl_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());

        logger.info("getGameUrl API Response Status Cod is Equal: " + statusCod);
        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseCode(),29);
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getDescription(),"SessionExpired", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseObject(),"null");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    @Description("Verify getURL API_s response with invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void GetUrlAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = getUrlAPI(partnerID,productID,clientId,capturedToken+"1");
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft01ApiVariables_getProductUrl_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft01ApiVariables_getProductUrl_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft01ApiVariables_getProductUrl_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());

        logger.info("getGameUrl API Response Status Cod is Equal: " + statusCod);
        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseCode(),28);
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getDescription(),"SessionNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseObject(),"null");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    @Description("Verify getURL API_s response with invalid ClientID")
    @Severity(SeverityLevel.NORMAL)
    public void GetUrlAPIValidateResponseWithInvalidClientID() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = getUrlAPI(partnerID,productID,100,capturedToken);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft01ApiVariables_getProductUrl_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft01ApiVariables_getProductUrl_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft01ApiVariables_getProductUrl_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());

        logger.info("getGameUrl API Response Status Cod is Equal: " + statusCod);
        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseCode(),22);
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getDescription(),"ClientNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseObject(),"null");
        softAssert.assertAll();
    }


    @Test(priority = 4)
    @Description("Verify getURL API_s response with invalid ProductID")
    @Severity(SeverityLevel.NORMAL)
    public void GetUrlAPIValidateResponseWithInvalidProductID() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = getUrlAPI(partnerID,-10,clientId,capturedToken);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft01ApiVariables_getProductUrl_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft01ApiVariables_getProductUrl_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft01ApiVariables_getProductUrl_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());

        logger.info("getGameUrl API Response Status Cod is Equal: " + statusCod);
        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseCode(),43);
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getDescription(),"ProductNotFound", "Error Description: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseObject(),"null");
        softAssert.assertAll();
    }




}
