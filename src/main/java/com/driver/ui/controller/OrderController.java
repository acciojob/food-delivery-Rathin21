package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.converter.OrderConverter;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderServiceImpl orderServiceImpl;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{

		OrderDto orderDto = orderServiceImpl.getOrderById(id);
		OrderDetailsResponse order = OrderConverter.dtoToResponse(orderDto);
		return order;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		
		OrderDto orderDto = OrderConverter.detailsToDto(order);
		orderDto = orderServiceImpl.createOrder(orderDto);
		OrderDetailsResponse orderResponse = OrderConverter.dtoToResponse(orderDto);
		return orderResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		
		OrderDto orderDto = OrderConverter.detailsToDto(order);
		orderDto = orderServiceImpl.updateOrderDetails(id, orderDto);
		OrderDetailsResponse orderResponse = OrderConverter.dtoToResponse(orderDto);
		return orderResponse;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		
		orderServiceImpl.deleteOrder(id);
		OperationStatusModel operationStatusModel = OperationStatusModel.builder().
													operationName(RequestOperationName.DELETE.toString()).
													operationResult(RequestOperationStatus.SUCCESS.toString()).build();


		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDetailsResponse> orderList = new ArrayList<>();
		List<OrderDto> list = orderServiceImpl.getOrders();
		for(OrderDto orderDto : list){

			OrderDetailsResponse orderResponse = OrderConverter.dtoToResponse(orderDto);

			orderList.add(orderResponse);
		}
		return orderList;
	}
}
