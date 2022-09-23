package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testData.IqSoft_01_APIVariables_GetProductUrl_Response;

import java.io.IOException;

public class API_1_GetUrl extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;


    @BeforeClass
    public void setUpTestCase() throws UnirestException, IOException {
        HttpResponse<String> response = getUrlAPI();
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft01ApiVariables_getProductUrl_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("getGameUrl API ResponseCode is Equal: " + iqSoft01ApiVariables_getProductUrl_response.getResponseCode());

        iqSoft01ApiVariables_getProductUrl_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("getGameUrl API Description is Equal: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());

        iqSoft01ApiVariables_getProductUrl_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());
        logger.info("getGameUrl API ResponseObject is Equal: " + iqSoft01ApiVariables_getProductUrl_response.getResponseObject());

        try {
            String parts[] = iqSoft01ApiVariables_getProductUrl_response.getResponseObject().split("=");
            iqSoft01ApiVariables_getProductUrl_response.setAuthorizationToken(parts[2]);
        } catch (Exception e) {
            logger.info("authorizationToken has Exception : " + e);
        }

        logger.info("authorizationToken : " + iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken());

    }


    @Test(priority = 1)
    @Description("Verify getURL API_s Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void getUrlAPIValidateStatusCod() {
        logger.info("getGameUrl API Response Status Cod is Equal: " + statusCod);
        Assert.assertEquals(statusCod, 200);
    }


    @Test(priority = 2, dependsOnMethods = {"getUrlAPIValidateStatusCod"})
    @Description("Verify getGameUrl API_s Response ResponseCode = 0")
    @Severity(SeverityLevel.BLOCKER)
    public void getUrlAPIValidateResponseCodEqualsZero() {
        Assert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseCode(), 0);
    }


    @Test(priority = 3, dependsOnMethods = {"getUrlAPIValidateStatusCod"})
    @Description("Verify getGameUrl API_s Response Description = null")
    @Severity(SeverityLevel.BLOCKER)
    public void getUrlAPIValidateDescriptionNotNull() {
        Assert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getDescription(), "null");
    }


    @Test(priority = 4, dependsOnMethods = {"getUrlAPIValidateStatusCod"})
    @Description("Verify getGameUrl API_s Response ResponseObject != null")
    @Severity(SeverityLevel.BLOCKER)
    public void getUrlAPIValidateResponseObjectContainsHTTP() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals("null", iqSoft01ApiVariables_getProductUrl_response.getResponseObject());
        softAssert.assertTrue(iqSoft01ApiVariables_getProductUrl_response.getResponseObject().contains("http://ipls-stg.winsysgroup.com/games/?gameId="));
        softAssert.assertAll();
    }

    @Test(priority = 5, dependsOnMethods = {"getUrlAPIValidateStatusCod"})
    @Description("Verify getGameUrl API_s Response AuthorizationToken != null")
    @Severity(SeverityLevel.BLOCKER)
    public void getUrlAPIValidateAuthorizationTokenNotNull() {
        Assert.assertNotEquals(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), null);
    }


    @AfterClass
    public void tearDownTestCase() {

    }

}
