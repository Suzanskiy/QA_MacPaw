package mail;

import mail.model.MailBox;

import java.io.IOException;

public interface IMailApi {

    MailBox createNewMailBox() throws IOException;

    String getConfirmationCode(MailBox mailBox) throws IOException;

    void removeMailBox(MailBox box) throws IOException;
}
