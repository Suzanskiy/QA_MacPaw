/*
 * Copyright Sergey Suzanskiy (c) 2020.
 */

package PageObjects;

import mail.model.MailBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final String URI_MATCH = "https://rozetka.com.ua/";
    WebDriver driver;

    public HomePage(WebDriver driver) {
        if (!driver.getCurrentUrl().equals(URI_MATCH))
            throw new IllegalStateException(
                    "This is not the page you are expected"
            );
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // First Task selectors
    @FindBy(xpath = "/html/body/app-root/div/div[1]/app-rz-header/header/div/div[1]/div[3]/div[1]/p/a")
    private WebElement personalCabinet;

    @FindBy(css = ".auth-modal__register-link")
    private WebElement beginRegister;
    @FindBy(xpath = "/html/body[@class='overflow-hidden']/app-root/single-modal-window/div[@class='modal__holder modal__holder_show_animation modal__holder_size_medium']/div[@class='modal__content']/user-identification/register/div[@class='auth-modal']/form[@class='auth-modal__form ng-invalid ng-dirty ng-touched']/div[@class='form__row auth-modal__form-bottom']/button[@class='button button_size_large button_color_green auth-modal__submit']")
    private WebElement confirmRegister;

    @FindBy(xpath = "/html/body/app-root/single-modal-window/div[2]/div[2]/user-identification/register/div/form/fieldset[1]/input")
    private WebElement nameInput;
    @FindBy(xpath = "/html/body/app-root/single-modal-window/div[2]/div[2]/user-identification/register/div/form/fieldset[2]/input")
    private WebElement emailInput;
    @FindBy(xpath = "/html/body/app-root/single-modal-window/div[2]/div[2]/user-identification/register/div/form/fieldset[3]/div/input")
    private WebElement passwordInput;

    @FindBy(xpath = "/html/body/app-root/div/div[1]/div[2]/div/main/rz-cabinet-personal-information/rz-cabinet-contacts/section/div[1]/button/span")
    private WebElement editBtn;
    @FindBy(xpath = "/html/body/app-root/div/div[1]/div[2]/div/main/rz-cabinet-personal-information/rz-cabinet-contacts/section/form/div/ul/li[2]/div[2]/button")
    private WebElement confirmBtn;
    @FindBy(xpath = "/html/body/app-root/single-modal-window/div[2]/div[2]/rz-cabinet-confirm-email-modal/form/fieldset/div[1]/input")
    private WebElement codeInput;
    @FindBy(xpath = "/html/body/app-root/single-modal-window/div[2]/div[2]/rz-cabinet-confirm-email-modal/form/fieldset/div[2]/button[1]")
    private WebElement submitConfirmCode;
    @FindBy(xpath = "/html/body/app-root/single-modal-window/div[2]/div[2]/rz-cabinet-confirm-email-modal/rz-cabinet-error-modal/div/p[1]")
    private WebElement confirmMailMsg;

    //Second Task selectors
    @FindBy(xpath = "/html/body/app-root/div/div[1]/app-rz-header/header/div/div[2]/div[2]/form/div/div[1]/input")
    private WebElement searchInput;
    @FindBy(xpath = "/html/body/app-root/div/div[1]/app-rz-header/header/div/div[2]/div[2]/form/button")
    private WebElement findBtn;
    @FindBy(xpath = "/html/body/app-root/div/div[1]/app-rz-product/div/product-tab-main/div[1]/div[1]/div[2]/product-main-info/div/div/app-product-buy-btn/app-buy-button/button/span")
    private WebElement buyBtn;


    @FindBy(xpath = "/html/body/app-root/div[@class='wrapper central-wrapper js-wrapper']/div[1]/app-rz-search/div[@class='central-wrapper']/main/search-result/div[@class='layout layout_with_sidebar']/section[@class='content content_type_catalog js_content']/app-search-goods/ul[@class='catalog-grid']/li[@class='catalog-grid__cell catalog-grid__cell_type_slim'][1]/app-goods-tile-default/div[@class='goods-tile']/div[@class='goods-tile__inner']/div[@class='goods-tile__prices']/div[@class='goods-tile__price goods-tile__price_color_red']/p/span[@class='goods-tile__price-value']")
    private WebElement iphonePriceBefore;
    @FindBy(xpath = "/html/body/app-root/single-modal-window/div[2]/div[2]/rz-shopping-cart/div/div[1]/div/div/div")
    private WebElement iphonePriceAfter;
    @FindBy(xpath = "/html/body/app-root/div[@class='wrapper central-wrapper js-wrapper']/div[1]/app-rz-search/div[@class='central-wrapper']/main/search-result/div[@class='layout layout_with_sidebar']/section[@class='content content_type_catalog js_content']/app-search-goods/ul[@class='catalog-grid']/li[@class='catalog-grid__cell catalog-grid__cell_type_slim'][1]/app-goods-tile-default/div[@class='goods-tile']/div[@class='goods-tile__inner']/a[@class='goods-tile__heading']/span[@class='goods-tile__title']")
    private WebElement deviceNameBefore;
    @FindBy(xpath = "/html/body[@class='overflow-hidden']/app-root/single-modal-window/div[@class='modal__holder modal__holder_show_animation modal__holder_size_large']/div[@class='modal__content']/rz-shopping-cart/div[@class='cart']/ul[@class='cart-list']/li[@class='cart-list__item']/rz-cart-product/div[@class='cart-product']/div[@class='cart-product__body']/div[@class='cart-product__main']/a[@class='cart-product__title']")
    private WebElement deviceNameAfter;


    public void prepareRegister() throws InterruptedException {
        personalCabinet.click();
        Thread.sleep(500);
        beginRegister.click();
        Thread.sleep(500);
    }

    public void enterData(MailBox box) throws InterruptedException {
        nameInput.click();
        nameInput.sendKeys(box.getEmail().split("@")[0]);
        emailInput.sendKeys(box.getEmail());
        passwordInput.sendKeys(box.getKey());
        confirmRegister.click();
        Thread.sleep(2000);
    }

    public void confirmEmail(String code) throws InterruptedException {
        editBtn.click();
        Thread.sleep(500);
        confirmBtn.click();
        codeInput.click();
        codeInput.sendKeys(code);
        Thread.sleep(500);
        submitConfirmCode.click();
    }

    public String getConfirmationMsg() {
        return confirmMailMsg.getText();
    }


    public Double findIphonePriceBefore() throws InterruptedException {
        searchInput.click();
        searchInput.sendKeys("Apple iPhone 11 Pro");
        findBtn.click();
        Thread.sleep(2000);
        return Double.valueOf(iphonePriceBefore.getText().replace(" ", ""));
    }

    public String findDeviceNameBefore() {
        return deviceNameBefore.getText();
    }

    public String findDeviceNameAfter() {
        return deviceNameAfter.getText();
    }

    public Double buyIphone() throws InterruptedException {
        deviceNameBefore.click();
        buyBtn.click();
        Thread.sleep(1500);
        buyBtn.click();
        return Double.valueOf(iphonePriceAfter
                .getText()
                .replace(" ", "")
                .replace("₴", ""));
    }
}
