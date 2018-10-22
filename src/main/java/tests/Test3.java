package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Test;

public class Test3 extends AbstractTest {
    @Test(description = "Case 3. Creating and editing of message")
    //@Parameters({"Message", "NewMessage"})
    public void test() {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        Message firstMessage = Message.createRandom();

        messageListHelper.createNewMessage(firstMessage);

        Message secondMessage = Message.createRandom();

        //TODO Ну вот например тут совсем неочевидно, что messageListHelper проверит корректность операции редактирования, потому и совать туда асерт смысла не было
        messageListHelper.editMessage(firstMessage, secondMessage);
    }
}
