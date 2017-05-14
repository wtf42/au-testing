package eakimov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CameraCatalogTests {
    {
        //System.setProperty("webdriver.chrome.driver", "/home/user/testing/chromedriver");
    }

    private WebDriver driver = new ChromeDriver();

    @Before
    public void loadPage() {
        driver.get("https://market.yandex.ru/catalog/54761/list?local-offers-first=0&deliveryincluded=0&onstock=1");
    }

    @Test
    public void filterTest() {
        CatalogPage page = new CatalogPage(driver);

        CatalogPage page2 = page.apply(10000, 20000, "GoPro");

        assertFalse(page2.getCameraModels().isEmpty());
        assertTrue(page2.getCameraModels().stream().allMatch(m ->
                m.getModelPrice() >= 10000 &&
                m.getModelPrice() <= 20000 &&
                m.getModelName().startsWith("GoPro"))
        );
    }

    @Test
    public void contentsTest() {
        CatalogPage page = new CatalogPage(driver);
        assertFalse(page.getCameraModels().isEmpty());
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
