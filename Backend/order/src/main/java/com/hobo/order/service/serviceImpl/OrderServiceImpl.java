package com.hobo.order.service.serviceImpl;

import com.hobo.order.entity.CartEntity;
import com.hobo.order.entity.OrderEntity;
import com.hobo.order.model.CartDTO;
import com.hobo.order.model.OrderDTO;
import com.hobo.order.repository.CartRepository;
import com.hobo.order.repository.OrderRepository;
import com.hobo.order.service.OrderService;
import org.omg.CORBA.Object;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository cartRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderDTO checkAlreadyExists = readOrder(orderDTO.getOrderId());
        OrderDTO orderDTOCheck  = null;

        if (checkAlreadyExists != null)
            return orderDTOCheck;

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderDTO, orderEntity);
        OrderEntity orderEntityCheck = cartRepository.save(orderEntity);

        orderDTOCheck = new OrderDTO();
        BeanUtils.copyProperties(orderEntityCheck, orderDTOCheck);
        return orderDTOCheck;
    }

    @Override
    public OrderDTO readOrder(int orderItemId) {
        OrderDTO orderDTO = null;
        OrderEntity orderEntity = cartRepository.findOne(orderItemId);
        if (orderEntity != null) {
            orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderEntity, orderDTO);
        }
        System.out.println(orderDTO);
        return orderDTO;

    }

    @Override
    public OrderDTO deleteOrder(int orderItemId) {
        OrderDTO orderDTO = readOrder(orderItemId);
        if (orderDTO != null) {
            OrderEntity orderEntity = new OrderEntity();
            BeanUtils.copyProperties(orderDTO, orderEntity);
            cartRepository.delete(orderEntity);
        }
        return orderDTO;

    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        OrderDTO checkAlreadyExists = readOrder(orderDTO.getOrderId());

        OrderDTO orderDTOCheck = null;

        if (checkAlreadyExists == null)
            return orderDTOCheck;

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderEntity, orderDTO);
        OrderEntity orderEntityCheck = cartRepository.save(orderEntity);

        orderDTOCheck = new OrderDTO();
        BeanUtils.copyProperties(orderEntityCheck, orderDTOCheck);
        return orderDTOCheck;
    }


}

























































