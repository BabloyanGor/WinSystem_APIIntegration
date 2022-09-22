package testCases;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class API1_GetUrl extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;
    String responseCode;
    String description;
    String responseObject;

    private String authorizationToken;
    public String getAuthorizationToken(){
        return authorizationToken;
    }

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        jsonObjectBody = new JSONObject(getUrlAPI().getBody());
        statusCod = getUrlAPI().getStatus();
        Unirest.shutdown();
        responseCode = jsonObjectBody.get("ResponseCode").toString();
        logger.info("getGameUrl API ResponseCode is Equal: " + responseCode);
        description = jsonObjectBody.get("Description").toString();
        logger.info("getGameUrl API Description is Equal: " + description);
        responseObject = jsonObjectBody.get("ResponseObject").toString();
        logger.info("getGameUrl API ResponseObject is Equal: " + responseObject);
        try{
            String parts[] = responseObject.split("=");
            authorizationToken = parts[2];
        }
        catch (Exception e){
            logger.info("authorizationToken has Exception : " + e);
        }

        logger.info("authorizationToken : " + authorizationToken);
    }


    @Test(priority = 1)
    @Description("Verify game launching API_s Status Cod equals to 200")
    public void validateStatusCodTest() {
        logger.info("getGameUrl API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200,statusCod);
    }


    @Test(priority = 2,dependsOnMethods = { "validateStatusCodTest" })
    @Description("Verify game launching API_s ResponseCode = 0")
    public void validateResponseCodTest() {
        Assert.assertEquals("0",responseCode);
    }


    @Test(priority = 3,dependsOnMethods = { "validateStatusCodTest" })
    @Description("Verify game launching API_s Description = null")
    public void validateDescriptionTest() {
        Assert.assertEquals("null",description);
    }


    @Test(priority = 4,dependsOnMethods = { "validateStatusCodTest" })
    @Description("Verify game launching API_s ResponseObject != null")
    public void validateResponseObjectTest() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals("null",responseObject);
        softAssert.assertTrue(responseObject.contains("http://ipls-stg.winsysgroup.com/games/?gameId="));
        softAssert.assertAll();
    }


    @Test(priority = 5,dependsOnMethods = { "validateStatusCodTest" })
    @Description("Verify game launching API_s AuthorizationToken != null")
    public void validateAuthorizationTokenTest() {
        Assert.assertNotEquals(null,authorizationToken);
    }








    @AfterClass
    public void tearDown(){

    }

}
