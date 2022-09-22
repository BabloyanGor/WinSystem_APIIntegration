package testCases;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class API1_GetUrl extends BaseTest {


    @Test(priority = 1)
    @Description("Verify game launching API_s Response Object (need not be null)")
    public void Pass() throws UnirestException, IOException {
//        JSONObject jsonObjectResponseCode = new JSONObject(getUrlAPI().get("ResponseCode").toString());
//        JSONObject jsonObjectDescription = new JSONObject(getUrlAPI().get("Description").toString());
//        JSONObject jsonObjectResponseObject = new JSONObject(getUrlAPI().get("ResponseObject").toString());
        String ResponseCode = getUrlAPI().get("ResponseCode").toString();
        String Description = getUrlAPI().get("Description").toString();
        String ResponseObject = getUrlAPI().get("ResponseObject").toString();

        System.out.println("ResponseCode :   "+ResponseCode);
        System.out.println("Description :   "+Description);
        System.out.println("ResponseObject :   "+ResponseObject);
//        Assert.assertTrue(true);

    }
//
//    @Test(priority = 3)
//    @Description("Verify game launching API_s Response Object (need not be null)")
//    public void Fail() throws UnirestException {
//        authorizationAPI();
//        logger.info("Body is equal :   "+authorizationAPI());
//        logger.info("Body is equal :   "+authorizationAPI());
//        Assert.assertTrue(false);
//
//    }
//    @Test(priority = 2)
//    @Description("Verify game launching API_s Response Object (need not be null)")
//    public void Skipped() throws UnirestException {
//        authorizationAPI();
//        logger.info("Body is equal :   "+authorizationAPI());
//        logger.info("Body is equal :   "+authorizationAPI());
//        throw new SkipException("message");
//    }



//    APIVariables_GetProductUrl_Response gameLaunching = new APIVariables_GetProductUrl_Response();
//    APIVariables_GetProductUrl_Request apisVariables_gameLunching_Request_body = new APIVariables_GetProductUrl_Request();
//    int partnerID = 51;
//    @BeforeClass
//    public void setup() throws UnirestException, JSONException {
//        Gson gson = new Gson();
//
//        String url = "https://production.iqsoftllc.com/51/api/Integration/OpenGame";
//
//        apisVariables_gameLunching_Request_body.setPartnerId(51);
//        apisVariables_gameLunching_Request_body.setLanguageId("en");
//        apisVariables_gameLunching_Request_body.setToken("8e7e58ad-a241-4f82-918e-6be53a3e5481");
//        apisVariables_gameLunching_Request_body.setIsForMobile("false");
//        apisVariables_gameLunching_Request_body.setGameId(21001);
//        apisVariables_gameLunching_Request_body.setDomain("betfaro.com");
//        String requestBodyValue = gson.toJson(apisVariables_gameLunching_Request_body);
//
//        System.out.println(requestBodyValue);
//        try{
//            Unirest.setTimeouts(0, 0);
//            HttpResponse<JsonNode> response = Unirest.post(url)
//                    .header("Content-Type", "application/json")
//                    .body(requestBodyValue)
//                    .asJson();
//            logger.info("Game Launching API request was sent");
//
//            JSONObject responseBody = new JSONObject(response.getBody().toString());
//
//            System.out.println(responseBody);
//
//            gameLaunching.setCod(response.getCode());
//
//            gameLaunching.setResponseCode(Integer.parseInt(responseBody.getString("ResponseCode")));
//
//            gameLaunching.setDescription(responseBody.getString("Description"));
//
//            gameLaunching.setResponseObject(responseBody.getString("ResponseObject"));
//        }
//        catch(UnirestException e){
//            logger.info("Game Launching API request has an Exception");
//        }
//    }
//
//    @Test(priority = 1)
//    @Description("Verify game launching API_s Status cod (need to be 200)" )
//    public void codTest() {
//
//        Assert.assertEquals(gameLaunching.getCod(), 200);
//        logger.info("Game Launching API Response status was checked: --->" + gameLaunching.getCod());
//        logger.info("---------------------------------------------------------------");
//    }
//
//    @Test(priority = 2)
//    @Description("Verify game launching API_s Response cod  (need to be 0)")
//    public void responseCodeTest() {
//        Assert.assertEquals(gameLaunching.getResponseCode(), 0);
//        logger.info("Game Launching API Response Cod was checked: --->"+gameLaunching.getResponseCode());
//        logger.info("---------------------------------------------------------------");
//    }
//
//    @Test(priority = 3)
//    @Description("Verify game launching API_s Response Description is (need to be null)")
//    public void responseDescriptionTest() {
//        Assert.assertEquals(gameLaunching.getDescription(), null);
//        logger.info("Game Launching API Response Error Description was checked: --->"+ gameLaunching.getDescription());
//        logger.info("---------------------------------------------------------------");
//    }
//
//    @Test(priority = 4)
//    @Description("Verify game launching API_s Response Object (need not be null)")
//    public void responseObjectTest() {
//        Assert.assertNotEquals(gameLaunching.getResponseObject(), "null");
//        logger.info("Game Launching API Response Object was checked: --->"+gameLaunching.getResponseObject());
//        logger.info("---------------------------------------------------------------");
//    }











    @AfterClass
    public void tearDown(){

    }

}
