package ufba.gorjetav1;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TesteBasico {
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        // Na API 16 não consegue encontrar o elemento pelo id.
//		caps.setCapability("deviceName", "Genymotion Custom Phone - 4.1.1 - API 16 - 768x1280");
//		caps.setCapability("platformVersion", "4.1.1");
        // Por isso vamos usar a API 26.
        caps.setCapability("deviceName", "api26");
        caps.setCapability("platformVersion", "8.0.0");

        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "Appium");
        caps.setCapability("appPackage", "ufba.gorjetav1");
        caps.setCapability("appActivity", "MainActivity");
        caps.setCapability("skipUnlock","true");
        caps.setCapability("noReset","false");

        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }

    public MobileElement byId(String id) {
        return driver.findElementById("ufba.gorjetav1:id/" + id);
    }

    @Test
    public void basicTest () throws InterruptedException {
        MobileElement editText = byId("editText");
        MobileElement button = byId("button");
        MobileElement textView = byId("textView");

        editText.sendKeys("1234");
        button.click();
        String text = textView.getText();

        Assert.assertEquals("1234", text);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
