package com.example.transportmap.service;

import com.example.transportmap.model.User;
import com.example.transportmap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final List<User> privilegedUsers;

    public UserService(UserRepository userRepository,
                       @Value("${services.user.cache.size}") int cacheSize) {
        this.userRepository = userRepository;
        this.privilegedUsers = new ArrayList<>(cacheSize);
    }

    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUser(int id){
        return userRepository.getUser(id);
    }

    public List<User> getPrivilegedUsers() {
        return privilegedUsers;
    }
}
