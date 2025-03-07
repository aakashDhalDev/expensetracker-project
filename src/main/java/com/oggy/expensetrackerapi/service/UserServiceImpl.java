package com.oggy.expensetrackerapi.service;

import com.oggy.expensetrackerapi.entity.User;
import com.oggy.expensetrackerapi.entity.UserModel;
import com.oggy.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import com.oggy.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.oggy.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public User createUser(UserModel uModel) {
        if(userRepo.existsByEmail(uModel.getEmail())){
            throw new ItemAlreadyExistsException("User is already registered with the email: "+uModel.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(uModel, user);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User readUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for the ID: "+id));
    }

    @Override
    public User updateUser(UserModel user, Long id) {
        User existingUser = readUser(id);
        existingUser.setName(user.getName()!=null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail()!=null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword()!=null ? user.getPassword() : existingUser.getPassword());
        existingUser.setAge(user.getAge()!=null ? user.getAge() : existingUser.getAge());

        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = readUser(id);
        userRepo.delete(existingUser);
    }
}
