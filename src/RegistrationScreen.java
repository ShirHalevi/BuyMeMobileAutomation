import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationScreen {
    public void pressOnGoogleRegistration(AndroidDriver<MobileElement> driver) throws InterruptedException {
// this function press on google registration
        WebDriverWait waiter = new WebDriverWait(driver, 10000);
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("il.co.mintapp.buyme:id/googleButton"))));
        driver.findElement(By.id("il.co.mintapp.buyme:id/googleButton")).click();
    }

    public void chooseGoogleAccount(AndroidDriver<MobileElement> driver) throws InterruptedException {
        //choose google account
        WebDriverWait waiter = new WebDriverWait(driver, 10000);
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("com.google.android.gms:id/account_display_name"))));
        driver.findElement(By.id("com.google.android.gms:id/account_display_name")).click();
    }
}
