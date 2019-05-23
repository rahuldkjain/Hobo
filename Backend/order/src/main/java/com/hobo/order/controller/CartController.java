package com.hobo.order.controller;


import com.hobo.order.entity.CartEntity;
import com.hobo.order.model.CartDTO;
import com.hobo.order.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.xml.ws.Action;

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














    /*@RequestMapping(method = RequestMethod.GET, value = "/getone/{cart_item_id}"
            *//*, produces = MediaType.APPLICATION_JSON_VALUE*//*)
    public ResponseEntity<?> readCart(@PathVariable("cart_item_id")  Integer cart_item_id){

        System.out.println(cart_item_id);
        CartEntity cartEntity = cartService.findOne(cart_item_id);
        CartDTO cartDTO = new CartDTO();
        System.out.println(cartEntity);

        if(cartDTO == null){
            return new ResponseEntity<String>("Could not create", HttpStatus.OK);

        }
        BeanUtils.copyProperties(cartEntity,cartDTO);
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);
    }




    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO){

        System.out.println(cartDTO);
        CartEntity cartEntity = new CartEntity();
        BeanUtils.copyProperties(cartDTO,cartEntity);
        CartEntity cartEntityCreated  = cartService.save(cartEntity);
        return new ResponseEntity<CartDTO>(cartDTO,HttpStatus.CREATED);
    }*/

    /*@RequestMapping(method = RequestMethod.PUT, value = "/update")
    public CartDTO updateCart(@RequestParam CartDTO cartDTO){
        return cartService.updateCart(cartDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{cart_item_id}")
    public CartDTO deleteCart(@RequestParam int cart_item_id){
        return cartService.delete(cart_item_id);
    }*/

