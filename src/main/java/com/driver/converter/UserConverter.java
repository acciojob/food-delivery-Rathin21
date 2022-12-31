package com.driver.converter;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;

public class UserConverter {
    
    public static UserDto detailsToDto(UserDetailsRequestModel userDetails){

        return UserDto.builder().email(userDetails.getEmail()).firstName(userDetails.getFirstName()).
               lastName(userDetails.getLastName()).build();
    }

    public static UserResponse dtoToResponse(UserDto userDto){

        return UserResponse.builder().userId(userDto.getUserId()).firstName(userDto.getFirstName()).
               lastName(userDto.getLastName()).email(userDto.getEmail()).build();
    }
}
