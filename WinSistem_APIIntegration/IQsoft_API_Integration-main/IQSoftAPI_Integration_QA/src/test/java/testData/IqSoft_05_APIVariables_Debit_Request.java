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
    private int Amount = 1;

    @SerializedName("Token")
    @Expose
    private String Token ;

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId="EUR" ;



    // "ClientId":51,
// "UserName":"radian",
// "GameId":21002,
// "OperationTypeId":4,
// "BetState":2,

}
