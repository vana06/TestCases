package core;

import core.pages.CartPage;
import core.pages.MallMainPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductLayout extends HelperBase {

    private static final By MALL_CARD = By.xpath(".//div[contains(@class,\"mall-card\")]");
    private static final By ADD_TO_CART = By.xpath(".//*[contains(text(), \"Добавить в корзину\")]");
    private static final By GO_TO_CART = By.xpath(".//*[contains(text(), \"Перейти в корзину\")]");
    private static final By NAME = By.xpath(".//div[contains(@class,\"mall-card_t mb-4x\")]");
    private static final By CLOSE_BUTTON = By.xpath(".//div[@class=\"modal-new_close\"]/a");
    private static final By SHARE_BUTTON = By.xpath(".//div[contains(@class,\"mall-widget_item\")]");
    private static final By SHARE_NOW = By.xpath(".//a[contains(@class,\"u-menu_li\")]");

    public ProductLayout(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не загрузился слой выбранного товара",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MALL_CARD), 5, 500));
    }

    public ProductLayout addToChart() {
        Assert.assertTrue("Отсутствует кнопка добавления в корзину",click(ADD_TO_CART));
        return this;
    }

    public String getName() {
        String result = getText(NAME);
        Assert.assertNotNull("Отсутствует имя товара", result);
        return result;
    }

    public MallMainPage close() {
        Assert.assertTrue("Отсутствует кнопка закрытия товара",click(CLOSE_BUTTON));
        return new MallMainPage(driver);
    }

    public CartPage goToChart() throws NoSuchElementException {
        Assert.assertTrue("Отсутствует кнопка перехода в корзину",click(GO_TO_CART));
        //throw new NoSuchElementException("Чтобы перейти в корзину со страницы товара, сперва надо добавить товар в корзину");
        return new CartPage(driver);
    }

    public ProductLayout clickShareNow(){
        Assert.assertTrue("Отсутствует кнопка поделится товаром",click(SHARE_BUTTON));
        Assert.assertTrue("Отсутствует кнопка опубликовать сейчас на стене",click(SHARE_NOW));
        return this;
    }
}
