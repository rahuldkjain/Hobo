package com.hobo.order.service;

import com.hobo.order.exceptions.cartExceptions.CartAlreadyExists;
import com.hobo.order.exceptions.cartExceptions.CartNotFound;
import com.hobo.order.entity.CartEntity;
import com.hobo.order.model.CartDTO;
import com.hobo.order.model.UserCartDTO;

import java.util.List;

public interface CartService {

    CartDTO createCart(CartDTO cartDTO) throws CartAlreadyExists;
    CartDTO readCart(int cartItemId)throws CartNotFound;
    CartDTO deleteCart(int cartItemId) throws  CartNotFound;
    CartDTO updateCart(CartDTO cartDTO) throws  CartNotFound;

    List<UserCartDTO> userCart(String emailId);
    void deleteCarts(String emailId);
    UserCartDTO updateQuantity(int cartItemId, int quantity);
}

