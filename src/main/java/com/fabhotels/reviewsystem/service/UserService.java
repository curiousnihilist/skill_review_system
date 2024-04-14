package com.fabhotels.reviewsystem.service;

import com.fabhotels.reviewsystem.dto.ResponseObj;
import com.fabhotels.reviewsystem.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    public ResponseObj fetchAllUsers();
    public ResponseObj fetchUserById(Integer id) throws Exception;


}
