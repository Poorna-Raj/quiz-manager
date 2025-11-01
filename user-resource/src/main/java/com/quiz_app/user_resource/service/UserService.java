package com.quiz_app.user_resource.service;
import java.util.Optional;
import com.quiz_app.user_resource.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.quiz_app.user_resource.data.User;


import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    @Autowired

    private UserRepository userRepository;

    // Create or Update User
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Delete user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Update user by ID
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setUsername(userDetails.getUsername());
        user.setAge(userDetails.getAge());
        user.setPassword(userDetails.getPassword());

        return userRepository.save(user);
    }
}
