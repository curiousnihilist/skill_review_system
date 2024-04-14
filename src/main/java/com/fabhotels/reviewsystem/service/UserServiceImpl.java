package com.fabhotels.reviewsystem.service;

import com.fabhotels.reviewsystem.dao.UserRepository;
import com.fabhotels.reviewsystem.dto.ResponseObj;
import com.fabhotels.reviewsystem.dto.UserDto;
import com.fabhotels.reviewsystem.enitities.User;
import com.fabhotels.reviewsystem.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository repo){
        this.userRepository = repo;
    }

    @Override
    public ResponseObj fetchAllUsers() {
       try{
           List<User> users = userRepository.findAll();
           List<UserDto> userDtos = users.stream().map((user) -> {
               return new UserDto(user.getUserId(),user.getName(),user.getCompany(),user.getYearOfExperience(),user.getSkills());
           }).collect(Collectors.toList());
           return CommonUtils.getResponseObjForSuccess(userDtos);
       }catch (Exception e){
           return CommonUtils.getResponseObjForFailure(e.getMessage());
       }
    }

    @Override
    public ResponseObj fetchUserById(Integer id) throws Exception {
        try{
            Optional<User> opUser = userRepository.findById(id);
            if (opUser.isEmpty()) {
                CommonUtils.getResponseObjForFailure("There's no user with id: "+id);
            }
            User user = opUser.get();
            UserDto dto = new UserDto(user.getUserId(), user.getName(), user.getCompany(), user.getYearOfExperience(),user.getSkills());
            return CommonUtils.getResponseObjForSuccess(dto);
        }catch (Exception e){
            return  CommonUtils.getResponseObjForFailure(e.getMessage());
        }
    }
}
