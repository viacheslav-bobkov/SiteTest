package elements;

import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ButtonElement extends BaseElement {
    public ButtonElement(WebDriver driver, By locator, String name) {
        super(driver, locator, name);
    }

    //TODO Какое отношение чекбокс имеет к кнопке?
    public boolean isCheckBoxSelected() {
        TestLogger.logMessage("Check that checkbox " + name + " is selected");
        return element().isSelected();
    }
}
