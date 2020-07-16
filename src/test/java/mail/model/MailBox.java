/*
 * Copyright Sergey Suzanskiy (c) 2020.
 */

package mail.model;

public class MailBox {
    private String email;
    private String key;
    private String confirmation;

    public MailBox() {
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
