import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

@FixMethodOrder(NAME_ASCENDING)
public class Main {
    private static AndroidDriver<MobileElement> driver;
    HomeScreen homeScreen = new HomeScreen();
    InformationScreen informationScreen = new InformationScreen();
    @Rule
    public TestName name = new TestName();
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeClass
    public static void setUpTest() throws MalformedURLException {// set up test- before class
        setReport();
        boolean driverEstablishAndAppLaunch = false;
        try {
            String appPa = SetUpXML.getAppSetting("appPackage");
            String appAc = SetUpXML.getAppSetting("appActivity");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
            capabilities.setCapability("appPackage", appPa);
            capabilities.setCapability("appActivity", appAc);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub/"), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Cant connect to driver");
            test.log(Status.FATAL,
                    "APP Connection Failed! " + e.getMessage());
            driverEstablishAndAppLaunch = false;
        } finally {
            if (driverEstablishAndAppLaunch) {
                test.log(Status.PASS, "App lunched successfully");
            }
        }
    }

    //Registration Test
    //RegistrationScreen registrationScreen=new RegistrationScreen();
    // @Test
    //  public void EnterToBuyMeAppAndRegister() throws InterruptedException
    //  {  registrationScreen.pressOnGoogleRegistration(driver);
    //  registrationScreen.chooseGoogleAccount(driver);    }
    //Extra Test
    //@Test
    // public  void Test05_extraTest() throws InterruptedException {
    //     homeScreen.extraTestGoToSettings(driver);
    //  }

    boolean chooseGift = false;

    @Test
    public void Test02_searchForAGift() throws InterruptedException { //choose category, buesness ,price and press on Buy button
        try {
            Thread.sleep(10000);
            test.pass("Home Screen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constant.screenshotPath + "1.Home Screen")).build());
            homeScreen.chooseCategory(driver);
            homeScreen.pickBuisness(driver);
            test.pass("After Category was chosen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constant.screenshotPath + "2.After Category was chosen")).build());
            homeScreen.enterGiftCardPrice(driver);
            test.pass("Price entered in Buiseness screen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constant.screenshotPath + "3.Price entered in Buiseness screen")).build());
            homeScreen.pressOnBuyButton(driver);
            chooseGift = true;
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Choose Gift failed" + e.getMessage());
            chooseGift = false;
        } finally {
            if (chooseGift) {
                test.log(Status.PASS, "Choose Gift Done successfully");
            }
        }
    }

    boolean fillDetailsInFirstScreen = false;

    @Test
    public void Test03_enterGiftCardDetails_FirstPage() throws InterruptedException {
        try {// enter gift card details in first screen
            test.pass("Gift Card details first screen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constant.screenshotPath + "4.Gift Card details first screen")).build());
            informationScreen.chooseEvent(driver);
            informationScreen.enterRecieverName(driver);
            informationScreen.enterBlessingText(driver);
            informationScreen.scrollDown(driver);
            informationScreen.enterSenderName(driver);
            informationScreen.pressOnContinueButton(driver);
            fillDetailsInFirstScreen = true;
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Fill Details in first screen failed" + e.getMessage());
            fillDetailsInFirstScreen = false;
        } finally {
            if (fillDetailsInFirstScreen) {
                test.log(Status.PASS, "Fill Details in first screen Done successfully");
            }
        }
    }

    boolean fillDetailsInSecondScreen = false;

    @Test
    public void Test04_enterGiftCardDetails_SecondPage() throws InterruptedException {
        //// enter gift card details in second screen
        try {
            test.pass("Gift Card details second screen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constant.screenshotPath + "5.Gift Card details second screen")).build());
            informationScreen.sendNowRadioButton(driver);
            informationScreen.swipe(driver);
            informationScreen.chooseMailOption(driver);
            informationScreen.PressOnContinue(driver);
            test.pass("Payment Screen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constant.screenshotPath + "6.Payment Screen")).build());
            fillDetailsInSecondScreen = true;
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Fill Details in second screen failed" + e.getMessage());
            fillDetailsInSecondScreen = false;
        } finally {
            if (fillDetailsInSecondScreen) {
                test.log(Status.PASS, "Fill Details in second screen Done successfully");
            }
        }

    }

    @AfterClass
    public static void afterClass() {
        // after class
        test.log(Status.INFO, "@After test "
                + "After test method");
        driver.quit();

        // build and flush report
        extent.flush();
    }

    private static String takeScreenShot(String ImagesPath) {
        //take a screenshot
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + ".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + ".png";
    }

    public static void setReport() { // create html reporter and set its settings
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("\\C:\\Users\\shir halevi\\Desktop\\qaexpert\\buyMeMobile\\reports\\extent.html");
        htmlReporter.setAppendExisting(true);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("BuyMeSanityTestWeb", "This is a BuyMe Sanity Test Mobile");
        extent.setSystemInfo("Tester", "Shir");
        test.log(Status.INFO, "@Before class");
    }
}

