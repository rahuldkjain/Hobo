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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    @Transactional(readOnly = false)
    public CartDTO createCart(CartDTO cartDTO) throws CartAlreadyExists {

        if(cartRepository.existsByUserEmailAndProductIdAndMerchantId(cartDTO.getUserEmail(),cartDTO.getProductId(),cartDTO.getMerchantId())){
            throw new CartAlreadyExists("Data already exists");
        }
        CartEntity cartEntity =new CartEntity();
        BeanUtils.copyProperties(cartDTO,cartEntity);
        cartEntity.setProductImage(cartDTO.getProductImage().get(0));
        CartEntity cartEntityCheck=cartRepository.save(cartEntity);
        BeanUtils.copyProperties(cartEntityCheck,cartDTO);
        ArrayList<String> tmpImage = new ArrayList<>();
        tmpImage.add(cartEntityCheck.getProductImage());
        cartDTO.setProductImage(tmpImage);
        return cartDTO;
    }

    @Override
    public CartDTO readCart(int cartItemId) throws CartNotFound {
        if(!cartRepository.exists(cartItemId)){
            throw new CartNotFound("Data not present");
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
    @Transactional(readOnly = false)
    public CartDTO updateCart(CartDTO cartDTO) throws CartNotFound{
        CartDTO checkAlreadyExists=readCart(cartDTO.getCartItemId());

        CartEntity cartEntity=new CartEntity();
        BeanUtils.copyProperties(cartDTO,cartEntity);
        CartEntity cartEntityCheck=cartRepository.save(cartEntity);
        BeanUtils.copyProperties(cartEntityCheck,cartDTO);
        return cartDTO;
    }

    @Override
    public List<CartEntity> userCart(String emailId) {
        List<CartEntity> cartEntities=cartRepository.findByUserEmail(emailId);
        return cartEntities;
    }

    @Override
    @Transactional(readOnly = false)
    public List<CartEntity> deleteCart(String emailId) {
        List<CartEntity> cartEntities=cartRepository.findByUserEmail(emailId);
        for (CartEntity cartEntity:cartEntities) {
            cartRepository.delete(cartEntity);
        }
        return cartEntities;
    }
}
