package com.hobo.order.service.serviceImpl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hobo.order.exceptions.orderExceptions.OrderAlreadyExists;
import com.hobo.order.exceptions.orderExceptions.OrderNotFound;
import com.hobo.order.entity.OrderEntity;
import com.hobo.order.model.OrderDTO;
import com.hobo.order.repository.OrderRepository;
import com.hobo.order.service.OrderService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) throws OrderAlreadyExists {
        if(orderRepository.exists(orderDTO.getOrderId())){
            throw new OrderAlreadyExists("Data already present");
        }
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderDTO, orderEntity);
        OrderEntity orderEntityCheck = orderRepository.save(orderEntity);
        BeanUtils.copyProperties(orderEntityCheck,orderDTO);
        return orderDTO;
    }

    @Override
    public OrderDTO readOrder(int orderItemId) throws OrderNotFound {
        if(!orderRepository.exists(orderItemId)){
            throw new OrderNotFound("Data not found");
        }
        OrderEntity orderEntity = orderRepository.findOne(orderItemId);

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderEntity, orderDTO);
        return orderDTO;
    }

    @Override
    public OrderDTO deleteOrder(int orderItemId) throws OrderNotFound{
        OrderDTO orderDTO = readOrder(orderItemId);
        if (orderDTO != null) {
            OrderEntity orderEntity = new OrderEntity();
            BeanUtils.copyProperties(orderDTO, orderEntity);
            orderRepository.delete(orderEntity);
        }
        return orderDTO;

    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) throws OrderNotFound{
        //OrderDTO checkAlreadyExists = readOrder(orderDTO.getOrderId());

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderDTO, orderEntity);
        OrderEntity orderEntityCheck = orderRepository.save(orderEntity);

        return orderDTO;
    }

    @Override
    public OrderDTO updateDeliveryDate(Integer orderId, Date deliveryDate) throws OrderNotFound{
        OrderDTO orderDTO=readOrder(orderId);
        orderDTO.setDeliveryDate(deliveryDate);
        OrderDTO orderDTO1=updateOrder(orderDTO);
        return orderDTO1;
    }

    @Override
    public List<OrderEntity> getAllOrder(String email) {
        List<OrderEntity> orderEntities=orderRepository.findByUserEmailId(email);
        return orderEntities;
    }
}