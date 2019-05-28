package com.hobo.order.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobo.order.email.OrderEmail;
import com.hobo.order.exceptions.orderExceptions.OrderAlreadyExists;
import com.hobo.order.exceptions.orderExceptions.OrderNotFound;
import com.hobo.order.entity.OrderEntity;
import com.hobo.order.model.OrderDTO;
import com.hobo.order.service.OrderService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public JSONObject readOrder(@RequestParam Integer orderId) throws OrderNotFound {
        try {
            OrderDTO orderDTO= orderService.readOrder(orderId);
            JSONParser parser = new JSONParser();
            ObjectMapper mapper = new ObjectMapper();
            JSONObject data = (JSONObject)parser.parse(mapper.writeValueAsString(orderDTO));
            data.replace("deliveryDate",orderDTO.getDeliveryDate().toString());
            data.replace("orderDate",orderDTO.getOrderDate().toString());
            JSONObject response=getJSONResponse(data);
            response.replace("message", "success", "Order fetch successful");
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "", consumes = {"application/json"})
    public JSONObject createOrder(@RequestBody OrderDTO orderDTO) throws OrderAlreadyExists {
        try {
            OrderDTO orderDTO1= orderService.createOrder(orderDTO);
            JSONObject response=getJSONResponse(orderDTO1);
            response.replace("message", "success", "Order successful");
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("")
    public JSONObject updateDTO(@RequestBody OrderDTO orderDTO) throws OrderNotFound{
        try {
            OrderDTO orderDTO1= orderService.updateOrder(orderDTO);
            JSONObject response=getJSONResponse(orderDTO1);
            response.replace("message", "success", "Order update successful");
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("")
    public JSONObject deleteOrder(@RequestParam int orderItemId) throws OrderNotFound{
        try {
            OrderDTO orderDTO= orderService.deleteOrder(orderItemId);
            JSONObject response=getJSONResponse(orderDTO);
            response.replace("message", "success", "Order delete successful");
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/updatedeliverydate")
    public JSONObject updateDeliveryDate(@RequestParam Integer orderId, @RequestParam String deliveryDate) throws OrderNotFound{
        try {
            Date dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(deliveryDate);

            System.out.println("String" + deliveryDate);
            System.out.println("Date" + dateOfBirth);

            OrderDTO orderDTO=orderService.updateDeliveryDate(orderId,dateOfBirth);
            JSONObject response=getJSONResponse(orderDTO);
            response.replace("message", "success", "Date update successful");
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        catch (ParseException p){
            p.printStackTrace();
        }
        return  null;
    }

    @GetMapping("/getall")
    public JSONObject getAll(@RequestParam String email){
        try {
            List<OrderEntity> orderEntities=orderService.getAllOrder(email);
            JSONParser parser = new JSONParser();
            JSONObject data = new JSONObject();
            JSONArray dataArray = new JSONArray();
            ObjectMapper mapper = new ObjectMapper();
            for (OrderEntity o:orderEntities) {
                data = (JSONObject)parser.parse(mapper.writeValueAsString(o));
                data.replace("deliveryDate",o.getDeliveryDate().toString());
                data.replace("orderDate",o.getOrderDate().toString());
                dataArray.add(data);
            }
            JSONObject response=getJSONResponse(dataArray);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/sendemail")
    public JSONObject sendEmail() {
        OrderEmail orderEmail = new OrderEmail();
        try {
            orderEmail.sendEmail("muditjoshi98@gmail.com","2019-05-27",(float)12000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject response=getJSONResponse("");
        response.replace("message","Email Successfully Sent");
        return response;
    }


    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");
        return response;
    }
}
