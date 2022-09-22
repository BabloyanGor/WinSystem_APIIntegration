//package utilities;
//
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import testCases.BaseTest;
//
//
//public class DriverFactory {
//    public static WebDriver driver;
//    public static ChromeOptions cOptions;
//    public static FirefoxOptions fOptions;
//    public static EdgeOptions eOptions;
//
//    public DriverFactory() {
//
//    }
//
//    String browser = "chrome";
//    String isHeadless = "false";
//    String siteUrl = "https://ipls-staging.winsysgroup.com/home";
//    public void initDriver() {
//        switch (browser) {
//            case "chrome":
//                BaseTest.logger.info("Browser for test will be chrome as chosen");
//                WebDriverManager.chromedriver().setup();
//                if (isHeadless.equals("true")){
//                    cOptions=new ChromeOptions();
//                    cOptions.addArguments("--headless", "--window-size=1920,1080");
////                    cOptions.setHeadless(true);
//                    driver= new ChromeDriver(cOptions);
//                }
//                else{
//                    driver = new ChromeDriver();
//                }
//                driver.manage().window().maximize();
//                driver.get(siteUrl);
//                break;
//
//            case "firefox":
//                BaseTest.logger.info("Browser for test will be firefox as chosen");
//                WebDriverManager.firefoxdriver().setup();
//                if (isHeadless.equals("true")){
//                    fOptions=new FirefoxOptions();
//                    fOptions.addArguments("--headless", "--window-size=1920,1080");
////                    fOptions.setHeadless(true);
//                    driver= new FirefoxDriver(fOptions);
//                }
//                else{
//                    driver = new FirefoxDriver();
//                }
//                driver.manage().window().maximize();
//                driver.get(siteUrl);
//                break;
//
//
//            case "edge":
//                BaseTest.logger.info("Browser for test will be edge as chosen");
//                WebDriverManager.edgedriver().setup();
//                if (isHeadless.equals("true")){
//                    eOptions=new EdgeOptions();
//                    eOptions.addArguments("--headless", "--window-size=1920,1080");
////                    eOptions.setHeadless(true);
//                    driver= new EdgeDriver(eOptions);
//                }
//                else{
//                    driver = new EdgeDriver();
//                }
//                driver.manage().window().maximize();
//                driver.get(siteUrl);
//                break;
//
//
//            default:
//                BaseTest.logger.info("Browser for test will be chrome by default");
//                WebDriverManager.chromedriver().setup();
//                if (isHeadless.equals("true")){
//                    cOptions=new ChromeOptions();
//                    eOptions.addArguments("--headless", "--window-size=1920,1080");
////                    cOptions.setHeadless(true);
//                    driver= new ChromeDriver(cOptions);
//                }
//                else{
//                    driver = new ChromeDriver();
//                }
//
//                driver.manage().window().maximize();
//                driver.get(siteUrl);
//        }
//
//    }
//
//}
//
