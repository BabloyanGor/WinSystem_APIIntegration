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

public class IqSoft_API_6_RollBack_Credit_Positive_Test extends  BaseTest{
    JSONObject jsonObjectBody;
    int statusCod;

    double beforeCreditBalance;
    double afterCreditBalance;
    double afterRollBackBalance;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();
        String rollBackTransactionID = randomRollBackTransactionID();


        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        Unirest.shutdown();
        beforeCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());

        creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), currency,clientProductID,
                3, creditTransactionID, betAmountCredit,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        Unirest.shutdown();
        afterCreditBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());

        HttpResponse<String> responseRollBack = rollBackAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(),userName,
                clientProductID,creditTransactionID ,rollBackTransactionID,1);
        jsonObjectBody = new JSONObject(responseRollBack.getBody());
        statusCod = responseRollBack.getStatus();
        Unirest.shutdown();

        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        logger.info("RollBack API Response ResponseCode : " + iqSoft_06_apiVariables_rollBack_response.getResponseCode());

        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());
//        logger.info("RollBack API Response Description : " + iqSoft_06_apiVariables_rollBack_response.getDescription());



        HttpResponse<String> responseGetBalanceAfterRollBack = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        JSONObject jsonObjectBody = new JSONObject(responseGetBalanceAfterRollBack.getBody());
        Unirest.shutdown();
        afterRollBackBalance = Double.parseDouble(jsonObjectBody.get("Balance").toString());

    }


    @Test(priority = 1)
    @Description("Verify RollBack API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidateStatusCod() {
        logger.info("RollBack API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"RollBackAPIValidateStatusCod"})
    @Description("Verify RollBack API_s Validate Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0,"1");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), null,"2");

        softAssert.assertEquals(beforeCreditBalance , afterCreditBalance + betAmountCredit,"3");
        softAssert.assertEquals(afterRollBackBalance , beforeCreditBalance,"4");
        softAssert.assertAll();

    }

}
