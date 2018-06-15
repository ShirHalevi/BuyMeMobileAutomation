import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomeScreen {
    List<MobileElement> Categories = new ArrayList<MobileElement>();

    public void chooseCategory(AndroidDriver<MobileElement> driver) throws InterruptedException {
        //this function choose category
        Thread.sleep(5000);
        Categories = driver.findElements(By.id("il.co.mintapp.buyme:id/t_title"));
        Categories.get(Constant.categoryId).click();
    }

    List<MobileElement> Buisnesses = new ArrayList<MobileElement>();

    public void pickBuisness(AndroidDriver<MobileElement> driver) throws InterruptedException {
        //this function choose buisness
        Thread.sleep(5000);
        //WebDriverWait waiter = new WebDriverWait(driver, 10000);
        //waiter.until(ExpectedConditions.visibilityOfAllElements((WebElement) driver.findElements(By.id("il.co.mintapp.buyme:id/businessImage"))));

        Buisnesses = driver.findElements(By.id("il.co.mintapp.buyme:id/businessImage"));
        Buisnesses.get(Constant.BuisnesId).click();
    }

    public void enterGiftCardPrice(AndroidDriver<MobileElement> driver) {////this function enter the amount of the gift
        WebDriverWait waiter = new WebDriverWait(driver, 10000);
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("il.co.mintapp.buyme:id/priceEditText"))));
        driver.findElement(By.id("il.co.mintapp.buyme:id/priceEditText")).sendKeys(Constant.GiftCardPrice);
    }

    public void pressOnBuyButton(AndroidDriver<MobileElement> driver) throws InterruptedException {
        //this function press on buy me button
        WebDriverWait waiter = new WebDriverWait(driver, 10000);
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("il.co.mintapp.buyme:id/purchaseButton"))));
        driver.findElement(By.id("il.co.mintapp.buyme:id/purchaseButton")).click();
        Thread.sleep(4000);
    }

    List<MobileElement> relativeLayouts = new ArrayList<MobileElement>();

    public void extraTestGoToSettings(AndroidDriver<MobileElement> driver) throws InterruptedException {
        //this function print text after pressing on buy me button
        WebDriverWait waiter = new WebDriverWait(driver, 10000);
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("il.co.mintapp.buyme:id/profileTab"))));
        driver.findElement(By.id("il.co.mintapp.buyme:id/profileTab")).click();

        waiter.until(ExpectedConditions.visibilityOfAllElements((WebElement) driver.findElements(By.className("android.widget.TextView"))));
        // Thread.sleep(5000);?
        relativeLayouts = driver.findElements(By.className("android.widget.TextView"));
        relativeLayouts.get(Constant.relativeLayoutsIndex).click();

        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("il.co.mintapp.buyme:id/titleText"))));
        // Thread.sleep(5000);
        System.out.println(driver.findElement(By.id("il.co.mintapp.buyme:id/titleText")).getText());
        System.out.println(driver.findElement(By.id("il.co.mintapp.buyme:id/contentText")).getText());


    }
}
