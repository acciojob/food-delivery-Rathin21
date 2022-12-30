package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto order) {
        
        OrderEntity orderEntity = OrderEntity.builder().id(order.getId()).orderId(order.getOrderId()).
                                  items(order.getItems()).userId(order.getUserId()).cost(order.getCost()).
                                  status(order.isStatus()).build();
        
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);

        OrderDto orderDto = OrderDto.builder().id(orderEntity.getId()).userId(orderEntity.getUserId()).
                            orderId(orderEntity.getOrderId()).items(orderEntity.getItems()).
                            cost(orderEntity.getCost()).status(orderEntity.isStatus()).build();

        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        orderEntity.setId(order.getId());
        orderEntity.setUserId(order.getUserId());
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setItems(order.getItems());
        orderEntity.setCost(order.getCost());
        orderEntity.setStatus(order.isStatus());

        return order;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        orderRepository.delete(orderEntity);
        
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orderList = new ArrayList<>();
        Iterable<OrderEntity> list = orderRepository.findAll();
        for(OrderEntity orderEntity :list){

            OrderDto orderDto = OrderDto.builder().id(orderEntity.getId()).userId(orderEntity.getUserId()).
                            orderId(orderEntity.getOrderId()).items(orderEntity.getItems()).
                            cost(orderEntity.getCost()).status(orderEntity.isStatus()).build();

            orderList.add(orderDto);
        }
        return orderList;
    }
    
}