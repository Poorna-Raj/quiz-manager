package com.quiz_app.user_resource.service;

import com.quiz_app.user_resource.data.UserRepository;
import com.quiz_app.user_resource.data.User;
import com.quiz_app.user_resource.exception.ContentNotFoundException;
import com.quiz_app.user_resource.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //  Create or Update User with field validation
    public User saveUser(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    //  Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //  Get user by ID (no Optional return)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("User not found with ID: " + id));
    }

    //  Delete user by ID
    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ContentNotFoundException("Cannot delete. User not found with ID: " + id);
        }
        userRepository.deleteById(id);
        return true;
    }

    // Update user by ID
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("User not found with ID: " + id));
        validateUser(userDetails);

        user.setUsername(userDetails.getUsername());
        user.setAge(userDetails.getAge());
        user.setPassword(userDetails.getPassword());

        return userRepository.save(user);
    }


    //  User validation
    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }

        if (user.getAge() == null || user.getAge() < 0 || user.getAge() > 120) {
            throw new ValidationException("Invalid age. Must be between 0 and 120.");
        }
    }
}