package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIVariables_GetBalance_Request {


    @SerializedName("Token")
    @Expose
    private String Token;
    @SerializedName("ProductId")
    @Expose
    private int ProductId = 4;


    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }


}
