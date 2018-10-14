package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import helpers.ViewMessageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test2 extends AbstractTest {
    @Test(description = "Case 2. Creation and view message")
    @Parameters({"Message"})
    public void test(Message message) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();
        ViewMessageHelper viewMessageHelper = new ViewMessageHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        messageListHelper.tapCreateMessage();

        Message userMessage = messageListHelper.createMessage(message);

        viewMessageHelper.openMessageList(userMessage);

        messageListHelper.viewMessage(userMessage);

        viewMessageHelper.openMessageList(userMessage);

        messageListHelper.assertMessageIsCorrect(userMessage);
    }
}
