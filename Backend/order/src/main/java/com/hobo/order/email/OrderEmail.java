package com.hobo.order.email;

import com.sendgrid.*;

import java.io.IOException;


public class OrderEmail {

    public void sendEmail(String email, String date, Float price) throws IOException {
        /*try {
            SendGrid sg = new SendGrid("Key hai yaaha par chupi huyi hai");
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody("{"+
                    "\"from\": {\"email\": \"order@hobo.in\"},"+
                    "\"personalizations\": [{"+
                    "\"to\": [{\"email\": \""+email+"\"}],"+
                    "\"dynamic_template_data\": {\"email\": \""+email+"\", \"totalPrice\": "+price+", \"date\": \""+date+"\"}}],\"template_id\": \"d-710c6e4f8da54ce9b6b2328bea219db7\"}");
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
