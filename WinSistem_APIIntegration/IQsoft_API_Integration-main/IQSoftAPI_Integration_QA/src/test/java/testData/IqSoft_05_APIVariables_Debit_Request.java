package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_05_APIVariables_Debit_Request {

    @SerializedName("RoundId")
    @Expose
    private String RoundId;

    @SerializedName("TransactionId")
    @Expose
    private String TransactionId;

    @SerializedName("CreditTransactionId")
    @Expose
    private String CreditTransactionId;

    @SerializedName("ProductId")
    @Expose
    private int ProductId = 4;

    @SerializedName("Amount")
    @Expose
    private int Amount = 50;

    @SerializedName("Token")
    @Expose
    private String Token ;

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId="EUR" ;




    @SerializedName("BetState")
    @Expose
    private int BetState;

    @SerializedName("ClientId")
    @Expose
    private int ClientId = 51;

    @SerializedName("UserName")
    @Expose
    private String UserName;

    @SerializedName("GameId")
    @Expose
    private int GameId;

    @SerializedName("OperationTypeId")
    @Expose
    private int OperationTypeId;
// "ClientId":51,
// "UserName":"radian",
// "GameId":21002,
// "OperationTypeId":4,
// "BetState":2,


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

    public String getCreditTransactionId() {
        return CreditTransactionId;
    }

    public void setCreditTransactionId(String creditTransactionId) {
        CreditTransactionId = creditTransactionId;
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

    public int getBetState() {
        return BetState;
    }

    public void setBetState(int betState) {
        BetState = betState;
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public int getOperationTypeId() {
        return OperationTypeId;
    }

    public void setOperationTypeId(int operationTypeId) {
        OperationTypeId = operationTypeId;
    }
}
