package com.driver.converter;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;

public class FoodConverter {
    
    public static FoodDto DetailsToDto(FoodDetailsRequestModel foodDetails){

        return FoodDto.builder().foodName(foodDetails.getFoodName()).foodCategory(foodDetails.
                getFoodCategory()).foodPrice(foodDetails.getFoodPrice()).build();
    }

    public static FoodDetailsResponse dtoToResponse(FoodDto foodDto){

        return FoodDetailsResponse.builder().foodId(foodDto.getFoodId()).foodName(foodDto.getFoodName()).
               foodCategory(foodDto.getFoodCategory()).foodPrice(foodDto.getFoodPrice()).build();
    }
}
