package com.hobo.order.controller;


import com.hobo.order.model.CartDTO;
import com.hobo.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/read")
    public CartDTO readCart(@RequestParam Integer cartItemId) {
        try {
            return cartService.readCart(cartItemId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/create", consumes = {"application/json"})
    public CartDTO createCart(@RequestBody CartDTO cartDTO) {
        try {
            return cartService.createCart(cartDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/update")
    public CartDTO updateCart(@RequestBody CartDTO cartDTO) {
        try {
            return cartService.updateCart(cartDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/delete")
    public CartDTO deleteCart(@RequestParam int cart_item_id) {
        try {
            return cartService.deleteCart(cart_item_id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}














