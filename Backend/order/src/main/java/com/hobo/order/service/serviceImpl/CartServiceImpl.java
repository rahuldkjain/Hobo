package com.hobo.order.service.serviceImpl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hobo.order.exceptions.cartExceptions.CartAlreadyExists;
import com.hobo.order.exceptions.cartExceptions.CartNotFound;
import com.hobo.order.entity.CartEntity;
import com.hobo.order.model.CartDTO;
import com.hobo.order.model.UserCartDTO;
import com.hobo.order.repository.CartRepository;
import com.hobo.order.service.CartService;
import javafx.util.Pair;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    @Transactional
    public CartDTO createCart(CartDTO cartDTO) {

        CartEntity cartEntity = cartRepository.findByUserEmailAndProductIdAndMerchantId(cartDTO.getUserEmail(),cartDTO.getProductId(),cartDTO.getMerchantId());
        if(cartEntity != null){
            cartEntity.setQuantity(cartEntity.getQuantity()+cartDTO.getQuantity());
        }
        else {
            cartEntity = new CartEntity();
            BeanUtils.copyProperties(cartDTO,cartEntity);
        }
        CartEntity cartEntityCheck=cartRepository.save(cartEntity);
        BeanUtils.copyProperties(cartEntityCheck,cartDTO);
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
    @Transactional
    public CartDTO deleteCart(int cartItemId) throws CartNotFound{

        CartEntity cartEntity= cartRepository.findOne(cartItemId);
        cartRepository.delete(cartItemId);
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(cartEntity,cartDTO);
        return cartDTO;
    }

    @Override
    @Transactional
    public CartDTO updateCart(CartDTO cartDTO) throws CartNotFound{
        CartEntity cartEntity = cartRepository.findByUserEmailAndProductIdAndMerchantId(cartDTO.getUserEmail(),cartDTO.getProductId(),cartDTO.getMerchantId());
        if(cartEntity != null){
            BeanUtils.copyProperties(cartDTO,cartEntity);
            cartEntity = cartRepository.save(cartEntity);
            BeanUtils.copyProperties(cartEntity,cartDTO);
            return cartDTO;
        }
        else {
            throw new CartNotFound("Data not found");
        }
    }

    @Override
    public List<UserCartDTO> userCart(String emailId) {
        List<CartEntity> cartEntities=cartRepository.findByUserEmail(emailId);
        List<UserCartDTO> resultArray = new ArrayList<>();
        for (CartEntity it: cartEntities) {
            UserCartDTO userCartDTO = new UserCartDTO();
            RestTemplate restTemplate = new RestTemplate();
            final String url = "http://localhost:8083/merchant/getnameandstock/"+it.getMerchantId()+"/"+it.getProductId();
            ResponseEntity<JSONObject> response = restTemplate.getForEntity(url, JSONObject.class);
            BeanUtils.copyProperties(it,userCartDTO);
            if(response.getBody()!=null) {
                userCartDTO.setMerchantName((String) response.getBody().get("name"));
                userCartDTO.setStock((Integer) response.getBody().get("stock"));
                resultArray.add(userCartDTO);
            }
        }
        return resultArray;
    }

    @Override
    @Transactional
    public void deleteCarts(String emailId) {
        /*ArrayList<CartEntity> cartEntities=cartRepository.findByUserEmail(emailId);
        for (CartEntity cartEntity:cartEntities) {
            cartRepository.delete(cartEntity);
        }*/
        //cartRepository.delete(emailId);
    }

    @Override
    @Transactional
    public UserCartDTO updateQuantity(int cartItemId, int quantity) {
        CartEntity cartEntity = cartRepository.findOne(cartItemId);
        UserCartDTO userCartDTO = new UserCartDTO();
        if(cartEntity != null) {
            RestTemplate restTemplate = new RestTemplate();
            final String url = "http://localhost:8083/merchantproduct/checkqty/"+cartEntity.getMerchantId()+"/"+cartEntity.getProductId()+"/"+quantity;
            ResponseEntity<String> response
                    = restTemplate.getForEntity(url, String.class);
            if(response.getBody()!= null) {
                cartEntity.setQuantity(quantity);
            }
            cartEntity = cartRepository.save(cartEntity);
            BeanUtils.copyProperties(cartEntity, userCartDTO);
            userCartDTO.setMerchantName(response.getBody());
            return userCartDTO;
        }
        else {
            return null;
        }
    }
}