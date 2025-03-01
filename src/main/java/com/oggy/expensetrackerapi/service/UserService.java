package com.oggy.expensetrackerapi.service;

import com.oggy.expensetrackerapi.entity.User;
import com.oggy.expensetrackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);
}
