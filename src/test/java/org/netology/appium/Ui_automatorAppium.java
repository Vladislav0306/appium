package org.netology.appium;

import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.netology.appium.automator.Locator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Ui_automatorAppium {

    private AndroidDriver driver;
    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:apppackage", "C:\\Users\\Dell\\Desktop\\mqa-homeworks-main\\2.2 UI Automator\\sample\\app\\build\\intermediates\\apk\\androidTest\\debug\\app-debug-androidTest.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3200);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void TestEmptyText() {
        Locator main = new Locator(driver);
        String textBefore = main.textChanged.getText();
        main.input.sendKeys(" ");
        main.buttonChange.click();
        String textAfter = main.textChanged.getText();
        assertEquals(textBefore, textAfter);
    }

    @Test
    public void TestNewActivity() {
        Locator main = new Locator(driver);
        main.input.sendKeys("Netology");
        main.buttonActivity.click();
        String textAfter = main.activityText.getText();
        assertEquals("Netology", textAfter);
        driver.navigate().back();
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
