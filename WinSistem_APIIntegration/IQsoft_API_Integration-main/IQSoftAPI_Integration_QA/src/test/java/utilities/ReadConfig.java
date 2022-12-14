package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties pro;

    public ReadConfig() {
        File src = new File("./Configuration/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }


//    public String getGameLaunchURL() {
//        return pro.getProperty("gameLaunchURL");
//    }
    public String getCallbackUrl() {
        return pro.getProperty("callbackUrl");
    }

    //region <Getting Variables for gameLaunchURL API>
    public int getPartnerID() {
        return Integer.parseInt(pro.getProperty("PartnerId"));
    }

    public int getProductID() {
        return Integer.parseInt(pro.getProperty("ProductId"));
    }
    public int getClientProductID() {
        return Integer.parseInt(pro.getProperty("ClientProductId"));
    }


    public int getClientId() {
        return Integer.parseInt(pro.getProperty("ClientId"));
    }
    public double getBetAmountCredit() {
        return Double.parseDouble(pro.getProperty("BetAmountCredit"));
    }
    public double getBetAmountDebit() {
        return Double.parseDouble(pro.getProperty("BetAmountDebit"));
    }

    public String getUserName() {
        return pro.getProperty("UserName");
    }

    public String getCurrency() {
        return pro.getProperty("CurrencyId");
    }
    public String getUserToken() {
        return pro.getProperty("UserToken");
    }

    //endregion





}
