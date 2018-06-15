import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InformationScreen {
    List<MobileElement> events = new ArrayList<MobileElement>();
    List<MobileElement> TextViewClass = new ArrayList<MobileElement>();

    public void chooseEvent(AndroidDriver<MobileElement> driver) throws InterruptedException {
        ////this function choose event
        Thread.sleep(7000);
        TextViewClass = driver.findElements(By.className("android.widget.TextView"));
        TextViewClass.get(Constant.textViewClassIndex).click();
        events = driver.findElements(By.className("android.widget.CheckedTextView"));
        events.get(Constant.eventsIndex).click();
    }

    public void enterRecieverName(AndroidDriver<MobileElement> driver) {//this function entering reciever name
        WebDriverWait waiter = new WebDriverWait(driver, 10000);
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("il.co.mintapp.buyme:id/toEditText"))));
        driver.findElement(By.id("il.co.mintapp.buyme:id/toEditText")).sendKeys(Constant.recieverName);
        driver.hideKeyboard();
    }

    public void enterBlessingText(AndroidDriver<MobileElement> driver) {//this function entering blessing text
        driver.findElement(By.id("il.co.mintapp.buyme:id/blessEditText")).clear();
        driver.findElement(By.id("il.co.mintapp.buyme:id/blessEditText")).sendKeys(Constant.blessingText);
        driver.hideKeyboard();
    }

    public void scrollDown(AndroidDriver<MobileElement> driver) throws InterruptedException {
        //this function scroll down by clicking on the scroll button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("il.co.mintapp.buyme:id/scrollArrows")).click();// go down
    }

    public void enterSenderName(AndroidDriver<MobileElement> driver) throws InterruptedException {
        //this function entering the sender name
        WebDriverWait waiter = new WebDriverWait(driver, 10000);
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("il.co.mintapp.buyme:id/userFrom"))));

        driver.findElement(By.id("il.co.mintapp.buyme:id/userFrom")).click();
        driver.findElement(By.id("il.co.mintapp.buyme:id/userFrom")).clear();
        driver.findElement(By.id("il.co.mintapp.buyme:id/userFrom")).sendKeys(Constant.senderName);
        driver.hideKeyboard();
    }

    public void pressOnContinueButton(AndroidDriver<MobileElement> driver) {//this function press on continue button
        driver.findElement(By.id("il.co.mintapp.buyme:id/goNextButton")).click();
    }

    public void sendNowRadioButton(AndroidDriver<MobileElement> driver) throws InterruptedException {
        //this function press on send Now Radio Button
        WebDriverWait waiter = new WebDriverWait(driver, 10000);
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("il.co.mintapp.buyme:id/nowRadioButton"))));

        driver.findElement(By.id("il.co.mintapp.buyme:id/nowRadioButton")).click();
    }

    List<MobileElement> checkBoxesInInformationScreen = new ArrayList<MobileElement>();

    public void chooseMailOption(AndroidDriver<MobileElement> driver) throws InterruptedException {
        // choose mail check box
        Thread.sleep(5000);
        checkBoxesInInformationScreen = driver.findElements(By.id("il.co.mintapp.buyme:id/optionCheckBox"));
        checkBoxesInInformationScreen.get(2).click();
        driver.findElement(By.id("il.co.mintapp.buyme:id/description")).sendKeys(Constant.email);
        driver.hideKeyboard();
    }

    public void swipe(AndroidDriver<MobileElement> driver)//not finished
    {// swipe to continue button
        TouchAction action = new TouchAction(driver);
        Duration oneHunderedMillisDuration = Duration.ofMillis(100);
        LongPressOptions longPressOptions = new LongPressOptions();
        MobileElement fromElement = driver.findElement(By.id("il.co.mintapp.buyme:id/sendMethodTitle"));
        ElementOption elementOption = new ElementOption();
        elementOption.withElement(fromElement);
        PointOption toPointOption = new PointOption();
        toPointOption.withCoordinates(100, 1000);
        longPressOptions.withDuration(oneHunderedMillisDuration).withElement(elementOption).build();
        action.longPress(longPressOptions).moveTo(toPointOption).release().perform();

    }

    public void PressOnContinue(AndroidDriver<MobileElement> driver)//not finished
    { // press on continue button
        WebDriverWait waiter = new WebDriverWait(driver, 10000);
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("il.co.mintapp.buyme:id/goNextButton"))));
        driver.findElement(By.id("il.co.mintapp.buyme:id/goNextButton")).click();
    }
}
