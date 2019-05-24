package com.hobo.order.service;

import com.hobo.order.Exceptions.cartExceptions.CartAlreadyExists;
import com.hobo.order.Exceptions.cartExceptions.CartNotFound;
import com.hobo.order.entity.CartEntity;
import com.hobo.order.model.CartDTO;

import java.util.List;

public interface CartService {

    CartDTO createCart(CartDTO cartDTO) throws CartAlreadyExists;
    CartDTO readCart(int cartItemId)throws CartNotFound;
    CartDTO deleteCart(int cartItemId) throws  CartNotFound;
    CartDTO updateCart(CartDTO cartDTO) throws  CartNotFound;

    List<CartEntity> userCart(Integer userId);
    List<CartEntity> deleteCart(Integer userId);
}

