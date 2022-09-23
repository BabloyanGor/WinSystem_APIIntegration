package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.RandomStringUtils;

public class IqSoft_04_APIVariables_Credit_Request {

    public String generateRandomRoundID() {
        String randomNumber = RandomStringUtils.randomNumeric(10);
        return "RoundId-"+randomNumber;
    }

    @SerializedName("RoundId")
    @Expose
    private String RoundId = generateRandomRoundID();

    @SerializedName("TransactionId")
    @Expose
    private String TransactionId = generateRandomRoundID();

    @SerializedName("ProductId")
    @Expose
    private int ProductId = 4;

    @SerializedName("Amount")
    @Expose
    private int Amount = 1;

    @SerializedName("Token")
    @Expose
    private String Token ;

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId = "EUR";

    public String getRoundId() {
        return RoundId;
    }

    public void setRoundId(String roundId) {
        RoundId = roundId;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(String currencyId) {
        CurrencyId = currencyId;
    }

    @SerializedName("TypeId")
    @Expose
    private int TypeId = 1 ;

    @SerializedName("OperationTypeId")
    @Expose
    private int OperationTypeId = 3 ;

    @SerializedName("BetState")
    @Expose
    private int BetState = 1 ;

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public int getOperationTypeId() {
        return OperationTypeId;
    }

    public void setOperationTypeId(int operationTypeId) {
        OperationTypeId = operationTypeId;
    }

    public int getBetState() {
        return BetState;
    }

    public void setBetState(int betState) {
        BetState = betState;
    }
}
