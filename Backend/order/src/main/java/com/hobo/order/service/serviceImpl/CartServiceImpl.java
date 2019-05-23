package com.hobo.order.service.serviceImpl;

import com.hobo.order.entity.CartEntity;
import com.hobo.order.model.CartDTO;
import com.hobo.order.repository.CartRepository;
import com.hobo.order.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;


    @Override
    public CartDTO createCart(CartDTO cartDTO) {
        CartDTO checkAlreadyExists=readCart(cartDTO.getCartItemId());
        CartDTO cartDTOCheck=null;

        if(checkAlreadyExists!=null)
            return cartDTOCheck;

        CartEntity cartEntity =new CartEntity();
        BeanUtils.copyProperties(cartDTO,cartEntity);
        CartEntity cartEntityCheck=cartRepository.save(cartEntity);

        cartDTOCheck = new CartDTO();
        BeanUtils.copyProperties(cartEntityCheck,cartDTOCheck);
        return cartDTOCheck;    }

    @Override
    public CartDTO readCart(int cartItemId) {
        CartDTO cartDTO=null;
        CartEntity cartEntity=cartRepository.findOne(cartItemId);
        if(cartEntity!=null)
        {
            cartDTO=new CartDTO();
            BeanUtils.copyProperties(cartEntity,cartDTO);
        }
        System.out.println(cartDTO);
        return cartDTO;    }

    @Override
    public CartDTO deleteCart(int cartItemId) {
        CartDTO cartDTO=readCart(cartItemId);
        if(cartDTO!=null){
            CartEntity cartEntity=new CartEntity();
            BeanUtils.copyProperties(cartDTO,cartEntity);
            cartRepository.delete(cartEntity);
        }
        return cartDTO;
    }

    @Override
    public CartDTO updateCart(CartDTO cartDTO) {
        CartDTO checkAlreadyExists=readCart(cartDTO.getCartItemId());

        CartDTO cartDTOCheck=null;

        if(checkAlreadyExists==null)
            return cartDTOCheck;

        CartEntity cartEntity=new CartEntity();
        BeanUtils.copyProperties(cartEntity,cartDTO);
        CartEntity cartEntityCheck=cartRepository.save(cartEntity);

        cartDTOCheck=new CartDTO();
        BeanUtils.copyProperties(cartEntityCheck,cartDTOCheck);
        return cartDTOCheck;
    }
}
