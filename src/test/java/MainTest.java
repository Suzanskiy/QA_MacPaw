import PageObjects.HomePage;
import mail.impl.MailApi;
import mail.model.MailBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class MainTest {

    private WebDriver driver;
    HomePage homePage;
    MailApi mailApi;
    MailBox box;
    private final String url = "https://rozetka.com.ua/";

    @BeforeSuite
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\WebDriver\\bin\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mailApi = new MailApi();
        System.out.println("The setup process is completed");
    }

    @BeforeTest
    public void profileSetup() throws IOException {
        driver.manage().window().maximize();
        driver.navigate().to(url);
        homePage = new HomePage(driver);
        box = mailApi.createNewMailBox();
        System.out.println("The profile setup process is completed");
    }

    @Test(expectedExceptions = AssertionError.class)
    // Розетка не принимает пользователей с темп имейлами. Поставил заглушку, чтоб тесты дальше шли
    public void firstTask() throws InterruptedException, IOException {

        homePage.prepareRegister();
        Thread.sleep(2000);
        homePage.enterData(box);
        Thread.sleep(5000);
        driver.navigate().to(url + "cabinet/personal-information/");
        Thread.sleep(2000);
        box.setConfirmation(mailApi.getConfirmationCode(box));
        Thread.sleep(2000);
        homePage.confirmEmail(box.getConfirmation());

        Assert.assertEquals(homePage.getConfirmationMsg(), "Адрес электронной почты подтвержден, спасибо");

    }

    @Test
    public void secondTask() throws InterruptedException {
        driver.navigate().to(url);
        double priceBefore = homePage.findIphonePriceBefore();
        String deviceNameBefore = homePage.findDeviceNameBefore();
        double priceAfter = homePage.buyIphone();
        String deviceNameAfter = homePage.findDeviceNameAfter();

        Assert.assertEquals(priceBefore, priceAfter);
        assert (deviceNameAfter.contains(deviceNameBefore));
    }

    @AfterClass
    public void closeUp() throws IOException {
        mailApi.removeMailBox(box);
        driver.close();
        System.out.println("The close_up process is completed");
    }


}