package eakimov;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class CameraModel extends HtmlElement {
    @FindBy(className = "snippet-card__header")
    private WebElement modelName;
    @FindBy(className = "snippet-card__price")
    private WebElement modelPrice;

    public String getModelName() {
        return modelName.getText();
    }

    public int getModelPrice() {
        return Integer.parseInt(modelPrice.getText().replaceAll("[^\\d]", ""));
    }
}
