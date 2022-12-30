package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;



@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        
        UserEntity userEntity = UserEntity.builder().id(user.getId()).userId(user.getUserId()).
                                firstName(user.getFirstName()).
                                lastName(user.getLastName()).email(user.getEmail()).build();
        userRepository.save(userEntity);

        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);

        UserDto userDto = UserDto.builder().id(userEntity.getId()).userId(userEntity.getUserId()).
                          firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).
                          email(userEntity.getEmail()).build();

        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);

        UserDto userDto = UserDto.builder().id(userEntity.getId()).userId(userEntity.getUserId()).
                          firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).
                          email(userEntity.getEmail()).build();
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);

        userEntity.setId(user.getId());
        userEntity.setUserId(user.getUserId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());

        
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        
        UserEntity userEntity = userRepository.findByUserId(userId);
        userRepository.delete(userEntity);
        
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userList = new ArrayList<>();
        Iterable<UserEntity> list = userRepository.findAll();
        for(UserEntity userEntity : list){

            UserDto userDto = UserDto.builder().id(userEntity.getId()).userId(userEntity.getUserId()).
                          firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).
                          email(userEntity.getEmail()).build();

            userList.add(userDto);
        }

        return userList;
    }
    
}