package eakimov;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.*;

import java.util.List;

public class ModelFilter extends HtmlElement {
    @FindBy(id = "glf-pricefrom-var")
    private TextInput priceFrom;
    @FindBy(id = "glf-priceto-var")
    private TextInput priceTo;
    @FindBy(xpath = "//*[starts-with(@id,'glf-7893318')]/../..")
    private List<CameraMaker> cameraMakers;
    @FindBy(className = "button_action_n-filter-apply")
    private Button applyBtn;

    void apply(int from, int to, String name) {
        priceFrom.clear();
        priceFrom.sendKeys(Integer.toString(from));
        priceTo.clear();
        priceTo.sendKeys(Integer.toString(to));
        cameraMakers.stream().filter(m -> name.equals(m.name())).forEach(CameraMaker::set);
        applyBtn.click();
    }

    public static class CameraMaker extends HtmlElement {
        @FindBy(className = "checkbox__control")
        private CheckBox checkbox;
        @FindBy(className = "checkbox__label")
        private WebElement label;

        public String name() {
            return label.getText();
        }

        public void set() {
            click();
        }
    }
}
