package com.example.coursework2.service;

import com.example.coursework2.model.User;
import com.example.coursework2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Long> getPropertiesByType() {
        Map<String, Long> map = new HashMap<>();
        List<Object[]> list = userRepository.countByPropertyType();
        for(Object[] o : list) {
            map.put((String) o[0], ((Number) o[1]).longValue());
        }
        return map;
    }
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isValidUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
