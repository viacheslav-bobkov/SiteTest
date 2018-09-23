package helpers;

import component.AbstractComponent;
import data.Message;
import logging.TestLogger;
import pages.AbstractPage;
import pages.LoginPage;
import pages.MessageList;
import pages.ViewMessagePage;

public class MessageListHelper extends AbstractComponent {
    private MessageList messageList;
    private CreateMessageFormHelper createMessageFormHelper;

    public MessageListHelper() {
        messageList = new MessageList();
        createMessageFormHelper = new CreateMessageFormHelper();
    }

    //TODO Вместо String[] создай класс Message
    public Message createMessage() {
        String text = "text";//TODO Не костанатный текст, а генерируй случайную строку
        String headline = "headline"; //TODO Same
        createMessage(headline, text);
        return new Message(headline, text);
    }

    //TODO Тоже возвращай класс Message,и на вход прнимай класс Message
    public String[] createMessage(String headline, String text){

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Create Message
        String [] message = createMessageFormHelper.createNewMessage(headline, text, messageList);
        headline = message[0];
        text = message[1];

        //There is a created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(headline, text);

        return new String[] {headline, text};
    }

    //TODO Не очень понимаю зачем этот хелпер. Только ради асертов? Логически стоило бы сделать наоборот.
    //TODO Асерт на существование должен быть в CreateMessageFormHelper. Этот метод и выше выглядят дубликатами аналогов в CreateMessageFormHelper
    public String[] createTwoMessages(String headline1, String text1, String headline2, String text2) {

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Create Two Messages
        String[] message = createMessageFormHelper.createTwoMessages(headline1, text1, headline2, text2, messageList);

        headline1 = message[0];
        text1 = message[1];
        headline2 = message[2];
        text2 = message[3];

        //There is a created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(headline1, text1);

        //There is a created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(headline2, text2);

        return new String[] {headline1, text1, headline2, text2};
    }

    //TODO почему void? Почему не ViewMessagePage?
    //TODO Используй Message класс
    public void viewMessage(String headline, String text) {
        TestLogger.logMessage("Opening from the list to view the message with the values headline: " + headline + ", text: " + text);

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //View Message
        ViewMessagePage viewMessagePage =  messageList.openViewMessagePage(headline, text);

        //Assert Headline and Text values
        //TODO А зачем? Кто-то просил?
        viewMessagePage.verifyHeadlineValue(headline);
        viewMessagePage.verifyTextValue(text);

        //Tap 'Message List' button
        //TODO C какой стати мы идем обратно?
        viewMessagePage.clickMessageList();

        //There is a created object and headline and text fields contains early filled values
        //TODO Это еще зачем?
        messageList.assertMessageIsInList(headline, text);
    }

    //TODO Тоже дубликат с CreateMessageFormHelper
    public void editMessage(String headline, String text, String newHeadline, String newText) {
        TestLogger.logMessage("Opening from the list to edit the message with the values headline: " + headline + ", text" + text);

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Enter new Headline and Text
        String[] message = createMessageFormHelper.editMessage(headline, text, newHeadline, newText, messageList);

        newHeadline = message[0];
        newText = message[1];

        //There is a last created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(newHeadline, newText);
    }

    public void deleteMessage(String headline, String text) {
        TestLogger.logMessage("Deleting from the list the message: " + headline + ", text: " + text);

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Delete Message
        messageList.deleteMessage(headline, text);

        //Check that Message is removed
        messageList.isMessageIsNotInList(headline, text);
    }

    //TODO Тоже дубликат с CreateMessageFormHelper
    public void createMessageWithoutSaving(String headline, String text) {
        TestLogger.logMessage("Creating message without saving");

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Create message without saving it
        String[] message = createMessageFormHelper.createMessageWithoutSaving(headline, text, messageList);

        headline = message[0];
        text = message[1];

        //Check that Message is not saved
        messageList.isMessageIsNotInList(headline, text);
    }

    public void signInAnotherUser(String newUserName, String newPassWord, String expected) {
        TestLogger.logMessage("Tap 'Logout' button");

        //Perform logout
        messageList.clickLogOutButton();

        //Sign in as another user
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(newUserName, newPassWord);
        //TODO Не очень корректно использовать снова тот же messageList, если ты уже выполнил logout.
        //TODO В силу разных причин страницы могут нечаянно хранить какую-то информацию о своем текущем состоянии на основании истории действий.
        // TODO Такой подход в перспективе может породить много проблем, сложно отлаваливаемых
        messageList.assertUsername(expected);
    }

    //TODO Метод называется All, но передавать может два и только два сообщения, да еще и логин какой-то непонятный
    public void verifyAllUsersMessages(String headline1, String text1, String headline2, String text2, String login) {
        TestLogger.logMessage("Verify all users messages");

        //Select checkbox
        messageList.selectAllUsersCheckBox();

        //Verify All Messages
        messageList.assertMessageIsInList(headline1, text1);
        messageList.assertMessageIsInList(headline2, text2);


        TestLogger.logMessage("Verify " + login + " messages");
        messageList.removeAllUsersCheckBox();

        messageList.assertMessageIsInList(headline1, text1);
    }
}
