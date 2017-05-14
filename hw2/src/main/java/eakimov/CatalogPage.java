package eakimov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;

public class CatalogPage {
    private final WebDriver driver;

    @FindBy(className = "n-filter-panel-aside")
    private ModelFilter modelFilter;
    @FindBy(className = "snippet-card")
    private List<CameraModel> cameraModels;

    public CatalogPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
        this.driver = driver;
    }

    public CatalogPage apply(int from, int to, String name) {
        WebElement oldModelsList = driver.findElement(By.className("snippet-list"));

        modelFilter.apply(from, to, name);

        WebDriverWait wait = new WebDriverWait(driver, 20, 10000);
        wait.until(ExpectedConditions.stalenessOf(oldModelsList));
        //wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.className("filter-applied-results_state_loading"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("filter-applied-results")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("snippet-list_js_inited")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("snippet-card")));

        return new CatalogPage(driver);
    }

    List<CameraModel> getCameraModels() {
        return cameraModels;
    }
}
