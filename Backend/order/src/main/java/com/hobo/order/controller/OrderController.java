package com.hobo.order.controller;


import com.hobo.order.Exceptions.orderExceptions.OrderAlreadyExists;
import com.hobo.order.Exceptions.orderExceptions.OrderNotFound;
import com.hobo.order.entity.OrderEntity;
import com.hobo.order.model.OrderDTO;
import com.hobo.order.service.OrderService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
            JSONObject response=getJSONResponse(orderDTO);
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "", consumes = {"application/json"})
    public JSONObject createOrder(@RequestBody OrderDTO orderDTO) throws OrderAlreadyExists {
        try {
            OrderDTO orderDTO1= orderService.createOrder(orderDTO);
            JSONObject response=getJSONResponse(orderDTO1);
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("")
    public JSONObject updateDTO(@RequestBody OrderDTO orderDTO) throws OrderNotFound{
        try {
            OrderDTO orderDTO1= orderService.updateOrder(orderDTO);
            JSONObject response=getJSONResponse(orderDTO1);
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
    public JSONObject getAll(@RequestParam Integer userId){
        try {
            List<OrderEntity> orderEntities=orderService.getAllOrder(userId);
            JSONObject response=getJSONResponse(orderEntities);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
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
