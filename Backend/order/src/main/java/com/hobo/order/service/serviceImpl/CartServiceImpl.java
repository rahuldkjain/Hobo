package com.hobo.order.service.serviceImpl;

import com.hobo.order.exceptions.cartExceptions.CartAlreadyExists;
import com.hobo.order.exceptions.cartExceptions.CartNotFound;
import com.hobo.order.entity.CartEntity;
import com.hobo.order.model.CartDTO;
import com.hobo.order.repository.CartRepository;
import com.hobo.order.service.CartService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartDTO createCart(CartDTO cartDTO) throws CartAlreadyExists {

        if(cartRepository.exists(cartDTO.getCartItemId())){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content Already Exists");
            error.put("message", "Content you are inserting is already present in the database");
            throw new CartAlreadyExists(error);
        }

        CartEntity cartEntity =new CartEntity();
        BeanUtils.copyProperties(cartDTO,cartEntity);
        CartEntity cartEntityCheck=cartRepository.save(cartEntity);

        return cartDTO;
    }

    @Override
    public CartDTO readCart(int cartItemId) throws CartNotFound {
        if(!cartRepository.exists(cartItemId)){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new CartNotFound(error);
        }
        CartEntity cartEntity=cartRepository.findOne(cartItemId);
        CartDTO cartDTO=new CartDTO();
        BeanUtils.copyProperties(cartEntity,cartDTO);
        return cartDTO;
    }

    @Override
    public CartDTO deleteCart(int cartItemId) throws CartNotFound{
        CartDTO cartDTO=readCart(cartItemId);
        CartEntity cartEntity=new CartEntity();
        BeanUtils.copyProperties(cartDTO,cartEntity);
        cartRepository.delete(cartEntity);
        return cartDTO;
    }

    @Override
    public CartDTO updateCart(CartDTO cartDTO) throws CartNotFound{
        CartDTO checkAlreadyExists=readCart(cartDTO.getCartItemId());

        CartEntity cartEntity=new CartEntity();
        BeanUtils.copyProperties(cartDTO,cartEntity);
        CartEntity cartEntityCheck=cartRepository.save(cartEntity);
        return cartDTO;
    }

    @Override
    public List<CartEntity> userCart(Integer userId) {
        List<CartEntity> cartEntities=cartRepository.findByUserId(userId);
        return cartEntities;
    }

    @Override
    public List<CartEntity> deleteCart(Integer userId) {
        List<CartEntity> cartEntities=cartRepository.findByUserId(userId);
        for (CartEntity cartEntity:cartEntities) {
            cartRepository.delete(cartEntity);
        }
        return cartEntities;
    }
}
