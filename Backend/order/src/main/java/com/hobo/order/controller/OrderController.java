package com.hobo.order.controller;


import com.hobo.order.model.CartDTO;
import com.hobo.order.model.OrderDTO;
import com.hobo.order.service.CartService;
import com.hobo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/read")
    public OrderDTO readOrder(@RequestParam Integer orderId) {
        try {
            return orderService.readOrder(orderId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/create", consumes = {"application/json"})
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            return orderService.createOrder(orderDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/update")
    public OrderDTO updateDTO(@RequestBody OrderDTO orderDTO) {
        try {
            return  orderService.updateOrder(orderDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/delete")
    public OrderDTO deleteOrder(@RequestParam int orderItemId) {
        try {
            return orderService.deleteOrder(orderItemId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
