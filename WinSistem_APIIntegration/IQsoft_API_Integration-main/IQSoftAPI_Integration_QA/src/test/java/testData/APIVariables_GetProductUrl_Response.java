package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIVariables_GetProductUrl_Response {
    @SerializedName("ResponseCode")
    @Expose
    private int ResponseCode;
    @SerializedName("Description")
    @Expose
    private int Description;
    @SerializedName("ResponseObject")
    @Expose
    private String ResponseObject;

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public int getDescription() {
        return Description;
    }

    public void setDescription(int description) {
        Description = description;
    }

    public String getResponseObject() {
        return ResponseObject;
    }

    public void setResponseObject(String responseObject) {
        ResponseObject = responseObject;
    }


}
