package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.converter.FoodConverter;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;

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
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodServiceImpl;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{

		FoodDto foodDto = foodServiceImpl.getFoodById(id);
		FoodDetailsResponse response = FoodConverter.dtoToResponse(foodDto);
		return response;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto = FoodConverter.DetailsToDto(foodDetails);
		foodDto = foodServiceImpl.createFood(foodDto);
		FoodDetailsResponse response = FoodConverter.dtoToResponse(foodDto);
		return response;
		
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{

		FoodDto foodDto = FoodConverter.DetailsToDto(foodDetails);
		foodDto = foodServiceImpl.updateFoodDetails(id, foodDto);
		FoodDetailsResponse response = FoodConverter.dtoToResponse(foodDto);
		return response;
		
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{

		foodServiceImpl.deleteFoodItem(id);
		OperationStatusModel operationStatusModel = OperationStatusModel.builder().
													operationName(RequestOperationName.DELETE.toString()).
													operationResult(RequestOperationStatus.SUCCESS.toString()).build();


		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {

		List<FoodDetailsResponse> foodList = new ArrayList<>();
		List<FoodDto> list = foodServiceImpl.getFoods();
		for(FoodDto foodDto : list){

			FoodDetailsResponse response = FoodConverter.dtoToResponse(foodDto);

			foodList.add(response);
			
		}
		return foodList;
	}
}
