package com.oggy.expensetrackerapi.service;

import com.oggy.expensetrackerapi.entity.User;
import com.oggy.expensetrackerapi.entity.UserModel;
import com.oggy.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;

    @Override
    public User createUser(UserModel uModel) {
        User user = new User();
        BeanUtils.copyProperties(uModel, user);
        return userRepo.save(user);
    }
}
