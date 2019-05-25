package com.hobo.order.email;

import com.sendgrid.*;

import java.io.IOException;

public class OrderEmail {

    public void sendEmail() throws IOException {
        Email from = new Email("support@hobo.com");
        String subject = "HoBo : Order";
        Email to = new Email("okjoshiji@gmail.com");
        Content content = new Content("text/plain", "Your Order have been successfully placed...");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }

        /*try {
            SendGrid sg = new SendGrid("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");
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody("{"+
                    "\"from\": {\"email\": \"order@hobo.in\"},"+
                    "\"personalizations\": [{"+
                    "\"to\": [{\"email\": \"okjoshiji@gmail.com\"}],"+
                    "\"dynamic_template_data\": {\"subject\": \"HoBo : Order Successfully placed\",\"user\": \"Mudit Joshi\", \"totalPrice\": \"120998\", "+
                    "\"product\": [{\"name\": \"Mi Tv\",\"price\":\"1200\",\"image\" : \"https://images.pexels.com/photos/414612/pexels-photo-414612.jpeg\"}]"+
                    "}}],\"template_id\": \"d-5f32af2772a3420fb5008c5e9430997a\"}");
            System.out.println(request.getBody());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }*/
    }
}
