package pages;

import elements.ButtonElement;
import elements.InputElement;
import elements.LabelElement;
import logging.TestLogger;
import org.openqa.selenium.By;
import utils.Environment;

//TODO CreateMessagePage
//TODO Create и Edit message pages are almost same and should have own super class
public class CreateMessagePage extends AbstractPage {

    private static final By CREATE_MESSAGE_LABEL = By.xpath("//H1[text()= 'Create Message']");
    private static final By CREATE_BUTTON = By.cssSelector(".save");
    private static final By HEADLINE_FIELD = By.id("headline");
    private static final By TEXT_FIELD = By.id("text");
    private static final By MESSAGE_LIST_BUTTON = By.cssSelector(".list");

    private LabelElement createMessageLabel() {
        return new LabelElement(driver, CREATE_MESSAGE_LABEL, "Create Message Label");
    }

    private ButtonElement messageList() {
        return new ButtonElement(driver, MESSAGE_LIST_BUTTON, "Message List Button");
    }

    private ButtonElement createButton() {
        return new ButtonElement(driver, CREATE_BUTTON, "Create Button");
    }

    private InputElement inputHeadline() {
        return new InputElement(driver, HEADLINE_FIELD, "Headline Field");
    }

    private InputElement inputText() {
        return new InputElement(driver, TEXT_FIELD, "Text Field");
    }

    public void isCreateMessagePageOpened() {
        assertPageOpened(createMessageLabel(), "Create Message");
    }

    private void clickCreate() {
        TestLogger.logMessage("Tap 'Create' button");

        createButton().click();
    }

    private MessageList clickMessageList() {
        TestLogger.logMessage("Tap 'Message List' button");

        messageList().click();
        MessageList messageList = new MessageList();
        messageList.isMessageListPageOpened();
        return messageList;
    }

    public ViewMessagePage createMessage(String headline, String text) {

        if (headline == null) {
            headline = Environment.generateUniqueString();
        }

        if (text == null) {
            headline = Environment.generateUniqueString();
        }

        TestLogger.logMessage("Filling 'Create Message' form with value [headline] " + headline + " and [text] " + text);
        inputHeadline().enterValue(headline);
        inputHeadline().assertValue(headline);
        inputText().enterValue(text);
        inputText().assertValue(text);
        clickCreate();

        ViewMessagePage viewMessagePage = new ViewMessagePage();
        viewMessagePage.assertViewMessagePageOpened();

        return viewMessagePage;
    }

    public MessageList createMessageWithoutSaving(String headline, String text) {
        if (headline == null) {
            headline = "test";
        }

        if (text == null) {
            headline = "test";
        }

        TestLogger.logMessage("Filling 'Create Message' form with value [headline] " + headline + " and [text] " + text);
        inputHeadline().enterValue(headline);
        inputHeadline().assertValue(headline);
        inputText().enterValue(text);
        inputText().assertValue(text);
        clickMessageList();
        return new MessageList();
    }
}