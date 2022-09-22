package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APISVariables_Authorization_Request {

    @SerializedName("Token")
    @Expose
    private String Token;

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId;

    @SerializedName("PartnerId")
    @Expose
    private int PartnerId;

    @SerializedName("ProductId")
    @Expose
    private String ProductId;

    @SerializedName("LanguageId")
    @Expose
    private String LanguageId;



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

    public int getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(int partnerId) {
        PartnerId = partnerId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(String languageId) {
        LanguageId = languageId;
    }
}
