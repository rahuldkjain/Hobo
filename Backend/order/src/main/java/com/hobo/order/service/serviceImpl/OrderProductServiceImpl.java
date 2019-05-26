package com.hobo.order.service.serviceImpl;

import com.hobo.order.exceptions.orderProductExceptions.OrderProductAlreadyExists;
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
            throw new OrderProductAlreadyExists("Data already exists");
        }
        OrderProductEntity orderProductEntity=new OrderProductEntity();
        BeanUtils.copyProperties(orderProductDTO,orderProductEntity);
        orderProductEntity=orderProductRepository.save(orderProductEntity);
        BeanUtils.copyProperties(orderProductEntity,orderProductDTO);
        return orderProductDTO;
    }

    @Override
    public List<OrderProductEntity> readByOrderId(Integer orderId) {
        List<OrderProductEntity> orderProductEntities=orderProductRepository.findByOrderId(orderId);
        return orderProductEntities;
    }
}
