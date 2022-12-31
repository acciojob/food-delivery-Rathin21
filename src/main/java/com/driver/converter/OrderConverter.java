package com.driver.converter;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;

public class OrderConverter {
    
    public static OrderDto detailsToDto(OrderDetailsRequestModel orderDetails){

        return OrderDto.builder().userId(orderDetails.getUserId()).cost(orderDetails.getCost()).
               items(orderDetails.getItems()).build();
    }

    public static OrderDetailsResponse dtoToResponse(OrderDto orderDto){

        return OrderDetailsResponse.builder().orderId(orderDto.getOrderId()).userId(orderDto.getUserId()).
               cost(orderDto.getCost()).items(orderDto.getItems()).status(orderDto.isStatus()).build();
    }
}
