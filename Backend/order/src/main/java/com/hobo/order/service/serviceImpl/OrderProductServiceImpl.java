package com.hobo.order.service.serviceImpl;

import com.hobo.order.Exceptions.orderExceptions.OrderAlreadyExists;
import com.hobo.order.Exceptions.orderExceptions.OrderNotFound;
import com.hobo.order.Exceptions.orderProductExceptions.OrderProductAlreadyExists;
import com.hobo.order.Exceptions.orderProductExceptions.OrderProductNotFound;
import com.hobo.order.entity.OrderProductEntity;
import com.hobo.order.model.OrderProductDTO;
import com.hobo.order.repository.OrderProductRepository;
import com.hobo.order.service.OrderProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    public OrderProductDTO createOrderProduct(OrderProductDTO orderProductDTO) throws OrderProductAlreadyExists {
        if(orderProductRepository.exists(orderProductDTO.getIndexx())){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content Already Exists");
            error.put("message", "Content you are inserting is already present in the database");
            throw new OrderProductAlreadyExists(error);
        }
        OrderProductEntity orderProductEntity=new OrderProductEntity();
        BeanUtils.copyProperties(orderProductDTO,orderProductEntity);
        OrderProductEntity orderProductEntity1=orderProductRepository.save(orderProductEntity);

        return orderProductDTO;
    }

    @Override
    public List<OrderProductEntity> readByOrderId(Integer orderId) {
        List<OrderProductEntity> orderProductEntities=orderProductRepository.findByOrderId(orderId);
        return orderProductEntities;
    }
}
