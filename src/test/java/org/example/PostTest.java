package org.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PostTest {
    public static LoginPage loginPage;
    public static InboxPage inboxPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @AfterClass
    public static void tearDown () throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void postTest() {
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.clickLoginBtn();
        loginPage.inputPasswd(ConfProperties.getProperty("passd"));
        loginPage.clickPasswdBtn();
        inboxPage.clickInboxBtn();
        inboxPage.sendTxtArea();
        inboxPage.sendTxtSub();
        int name = inboxPage.findInboxMes("farit.valiahmetov");
        inboxPage.sendTxtMes(name);
        inboxPage.clickLastBtn();
    }
}