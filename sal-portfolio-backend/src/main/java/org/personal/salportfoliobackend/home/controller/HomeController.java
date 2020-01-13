package org.personal.salportfoliobackend.home.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.personal.salportfoliobackend.domain.User;
import org.personal.salportfoliobackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class HomeController {
    
    @Autowired
    UserService userService;
    
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @GetMapping
    public List<User> getAllUsers() {
        Optional<List<User>> users = userService.getAll();
        
        if (users.isEmpty()) {
            logger.warn("User domain record is empty");
            return new ArrayList<User>();
        }
        
        return users.get();
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        Optional<User> user = userService.getById(id);
        
        if (user.isEmpty()) {
            logger.warn("Provided ID does not match any User on record");
            return new User();
        }
        
        return user.get();
    }
}
