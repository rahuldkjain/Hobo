package com.hobo.order.service;

import com.hobo.order.model.CartDTO;

public interface CartService {


    //Cart API create
    CartDTO createCart(CartDTO cartDTO);

    //read from cart by ID
    CartDTO readCart(int cartItemId);

    //delete Cart by ID
    CartDTO deleteCart(int cartItemId);

    //Update/Add to cart
    CartDTO updateCart(CartDTO cartDTO);

}

