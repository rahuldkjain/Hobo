package com.hobo.order.controller;


import com.hobo.order.exceptions.cartExceptions.CartAlreadyExists;
import com.hobo.order.exceptions.cartExceptions.CartNotFound;
import com.hobo.order.entity.CartEntity;
import com.hobo.order.model.CartDTO;
import com.hobo.order.service.CartService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public JSONObject readCart(@RequestParam Integer cartItemId) throws CartNotFound {
        try {
            CartDTO cartDTO= cartService.readCart(cartItemId);
            JSONObject response=getJSONResponse(cartDTO);
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "", consumes = {"application/json"})
    public JSONObject createCart(@RequestBody CartDTO cartDTO) throws CartAlreadyExists {
        try {
            CartDTO cartDTO1= cartService.createCart(cartDTO);
            JSONObject response=getJSONResponse(cartDTO1);
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("")
    public JSONObject updateCart(@RequestBody CartDTO cartDTO) throws CartNotFound{
        try {
            CartDTO cartDTO1= cartService.updateCart(cartDTO);
            JSONObject response=getJSONResponse(cartDTO1);
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("")
    public JSONObject deleteCart(@RequestParam int cartItemId) throws CartNotFound{
        try {
            CartDTO cartDTO= cartService.deleteCart(cartItemId);
            JSONObject response=getJSONResponse(cartDTO);
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/usercart")
    public  JSONObject userCart(@RequestParam Integer userId){
        try {
            List<CartEntity> cartEntities=cartService.userCart(userId);
            JSONObject response=getJSONResponse(cartEntities);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;

    }

    @DeleteMapping("deleteallcart")
    public JSONObject delteCart(@RequestParam Integer userId){
        try {
            List<CartEntity> cartEntities=cartService.deleteCart(userId);
            JSONObject response=getJSONResponse(cartEntities);
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














