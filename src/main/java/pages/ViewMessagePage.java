package pages;

import component.TableManager;
import data.Message;
import elements.ButtonElement;
import logging.TestLogger;
import org.openqa.selenium.By;

public class ViewMessagePage extends AbstractPage {
    private static final By SHOW_MESSAGE_LABEL = By.xpath("//H1[text()='Show Message']");
    private static final By MESSAGE_LIST_BUTTON = By.cssSelector(".list");
    private static final By TABLE = By.cssSelector(".dialog");
    private static final By NEW_MESSAGE_BUTTON = By.cssSelector(".create");

    private static final int fieldNameCol = 1;
    private static final int fieldValueCol = 2;

    public ViewMessagePage() {
        super(SHOW_MESSAGE_LABEL, "Show Message Page");
    }

    private ButtonElement messageList() {
        return new ButtonElement(driver, MESSAGE_LIST_BUTTON, "Message List");
    }

    private ButtonElement newMessage() {
        return new ButtonElement(driver, NEW_MESSAGE_BUTTON, "New Message");
    }

    public MessageList clickMessageList() {
        TestLogger.logMessage("Tap 'Message List' button");

        messageList().click();
        MessageList messageList = new MessageList();
        messageList.assertPageOpened();
        return messageList;
    }

    public void assertMessage(Message message) {
        assertHeadlineValue(message.getHeadline());
        assertTextValue(message.getText());
    }

    private static TableManager tableOfFields() {
        return new TableManager(TABLE);
    }

    public void assertHeadlineValue(String expected) {
        assertFieldValue("Headline", expected);
    }

    public void assertTextValue(String expected) {
        assertFieldValue("Text", expected);
    }

    private void assertFieldValue(String fieldName, String expected) {
        TestLogger.logMessage("Check that field " + fieldName + " has value " + expected);
        String value = getFieldValue(fieldName);

        if (value.equalsIgnoreCase(expected)) {
            TestLogger.logMessage("Field \"" + fieldName + "\" has expected value: " + expected);
        } else {
            TestLogger.logError("Field \"" + fieldName + "\" has value:" + value + ", which is not expected( " + expected + ")");
        }
    }

    private String getFieldValue(String fieldName) {
        int iRow = tableOfFields().findRowIndexWithCellText(fieldNameCol, fieldName);

        return tableOfFields().getCellText(iRow, fieldValueCol);
    }

    public CreateMessagePage clickNewMessage() {
        TestLogger.logMessage("Tap 'Create Message' button");

        newMessage().click();
        CreateMessagePage createMessagePage = new CreateMessagePage();
        createMessagePage.assertPageOpened();
        return createMessagePage;
    }
}
/*private LabelElement showMessage() {
        return new LabelElement(driver, SHOW_MESSAGE_LABEL, "Show Message");
    }*/