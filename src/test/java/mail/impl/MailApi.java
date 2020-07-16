/*
 * Copyright Sergey Suzanskiy (c) 2020.
 */

package mail.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mail.IMailApi;
import mail.model.MailBox;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MailApi implements IMailApi {
    private final String END_POINT = "https://post-shift.ru/api.php";
    OkHttpClient client;
    Request request;
    Gson gson;

    public MailApi() {
        client = new OkHttpClient().newBuilder()
                .build();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
    }

    @Override
    public MailBox createNewMailBox() throws IOException {

        request = new Request.Builder()
                .url(END_POINT + "?action=new&type=json")
                .method("GET", null)
                .addHeader("Cookie", "PHPSESSID=dqqrj9uvh8t024n5g7t0pfq8b3")
                .build();
        Response response = client.newCall(request).execute();

        assert response.body() != null;

        return gson.fromJson(response.body().string(), MailBox.class);
    }

    @Override
    public String getConfirmationCode(MailBox mailBox) throws IOException {

        request = new Request.Builder()
                .url(END_POINT + "?action=getmail&key=" + mailBox.getKey() + "&id=1")
                .method("GET", null)
                .addHeader("Cookie", "PHPSESSID=dqqrj9uvh8t024n5g7t0pfq8b3")
                .build();
        Response response = client.newCall(request).execute();
        String code = response.body().string().replace("Временный код для подтверждения регистрации: ", "");
        System.out.print("Confirmation code is: " + code);
        return code;
    }

    @Override
    public void removeMailBox(MailBox box) throws IOException {

        request = new Request.Builder()
                .url(END_POINT + "?action=delete&key=" + box.getKey())
                .method("GET", null)
                .addHeader("Cookie", "PHPSESSID=dqqrj9uvh8t024n5g7t0pfq8b3")
                .build();
        client.newCall(request).execute();
    }
}
