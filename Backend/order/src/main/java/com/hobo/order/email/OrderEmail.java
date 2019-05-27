package com.hobo.order.email;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class OrderEmail {

    public void sendEmail() throws MailjetException, MailjetSocketTimeoutException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(System.getenv("MJ_APIKEY_PUBLIC"), System.getenv("MJ_APIKEY_PRIVATE"), new ClientOptions("v3.1"));
        JSONObject from = new JSONObject();
        from.put("Email", "order@hobo.com");
        from.put("Name", "Hobo Pilot");
        JSONArray toArray = new JSONArray();
        JSONObject to = new JSONObject();
        to.put("Email", "mudit.joshi@coviam.com");
        to.put("Name", "Mudit Joshi");
        toArray.add(to);
        JSONObject message = new JSONObject();
        message.put(Emailv31.Message.FROM, from);
        message.put(Emailv31.Message.TO, toArray);
        message.put(Emailv31.Message.SUBJECT, "Your email flight plan!");
        message.put(Emailv31.Message.TEXTPART, "Dear passenger 1, welcome to Mailjet! May the delivery force be with you!");
        message.put(Emailv31.Message.HTMLPART, "<h3>Dear passenger 1, welcome to <a href=\"https://www.mailjet.com/\">Mailjet</a>!</h3><br />May the delivery force be with you!");
        JSONArray messageArray = new JSONArray();
        messageArray.add(message);
        request = new MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES,messageArray);
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
