package com.hobo.order.service.serviceImpl;

import com.hobo.order.exceptions.orderProductExceptions.DontHaveStock;
import com.hobo.order.exceptions.orderProductExceptions.OrderProductAlreadyExists;
import com.hobo.order.entity.OrderProductEntity;
import com.hobo.order.model.OrderProductDTO;
import com.hobo.order.repository.OrderProductRepository;
import com.hobo.order.service.OrderProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    @Transactional
    public OrderProductDTO createOrderProduct(OrderProductDTO orderProductDTO) throws OrderProductAlreadyExists, DontHaveStock {
        if(orderProductRepository.exists(orderProductDTO.getIndexx())){
            throw new OrderProductAlreadyExists("Data already exists");
        }
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8083/merchantproduct/confirmqty/"+orderProductDTO.getMerchantId()+"/"+orderProductDTO.getProductId()+"/"+orderProductDTO.getQuantity();
        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);
        OrderProductEntity orderProductEntity = new OrderProductEntity();
        if(response.getBody()!= null) {
            BeanUtils.copyProperties(orderProductDTO,orderProductEntity);
            orderProductEntity=orderProductRepository.save(orderProductEntity);
            BeanUtils.copyProperties(orderProductEntity,orderProductDTO);
        }
        else {
            throw new DontHaveStock("Sock Finishe");
        }
        return orderProductDTO;
    }

    @Override
    public List<OrderProductEntity> readByOrderId(Integer orderId) {
        List<OrderProductEntity> orderProductEntities=orderProductRepository.findByOrderId(orderId);
        return orderProductEntities;
    }
}
