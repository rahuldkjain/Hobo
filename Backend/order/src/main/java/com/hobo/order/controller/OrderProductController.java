package com.hobo.order.controller;

import com.hobo.order.Exceptions.orderProductExceptions.OrderProductAlreadyExists;
import com.hobo.order.entity.OrderProductEntity;
import com.hobo.order.model.OrderProductDTO;
import com.hobo.order.service.OrderProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderproduct")
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;

    @PostMapping("")
    public JSONObject createOrderProduct(@RequestBody OrderProductDTO orderProductDTO) throws OrderProductAlreadyExists {
        try {
            OrderProductDTO orderProductDTO1=orderProductService.createOrderProduct(orderProductDTO);
            JSONObject response=getJSONResponse(orderProductDTO1);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("")
    public JSONObject readByOrderId(@RequestParam Integer orderId)
    {
        try {
            List<OrderProductEntity> orderProductEntities=orderProductService.readByOrderId(orderId);
            JSONObject response=getJSONResponse(orderProductEntities);
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
