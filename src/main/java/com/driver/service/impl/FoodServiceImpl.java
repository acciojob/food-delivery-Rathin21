package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDto food) {
        
        FoodEntity foodEntity = FoodEntity.builder().id(food.getId()).foodId(food.getFoodId()).
                                foodCategory(food.getFoodCategory()).foodName(food.getFoodName()).
                                foodPrice(food.getFoodPrice()).build();

        foodRepository.save(foodEntity);

        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);

        FoodDto foodDto = FoodDto.builder().id(foodEntity.getId()).foodId(foodEntity.getFoodId()).
                          foodName(foodEntity.getFoodName()).foodCategory(foodEntity.getFoodCategory()).
                          foodPrice(foodEntity.getFoodPrice()).build();
            

        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);

        foodEntity.setId(foodDetails.getId());
        foodEntity.setFoodId(foodDetails.getFoodId());
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());

        

        return foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        
        FoodEntity foodEntity = foodRepository.findByFoodId(id);
        foodRepository.delete(foodEntity);
       
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodDto> foodList=new ArrayList<>();
        Iterable<FoodEntity> list = foodRepository.findAll();
        for(FoodEntity foodEntity : list){

            FoodDto foodDto = FoodDto.builder().id(foodEntity.getId()).foodId(foodEntity.getFoodId()).
                          foodName(foodEntity.getFoodName()).foodCategory(foodEntity.getFoodCategory()).
                          foodPrice(foodEntity.getFoodPrice()).build();

            foodList.add(foodDto);
        }
        return foodList;
    }
    
}