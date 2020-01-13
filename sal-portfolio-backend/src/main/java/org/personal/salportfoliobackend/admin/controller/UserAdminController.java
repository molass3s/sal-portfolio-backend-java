package org.personal.salportfoliobackend.admin.controller;

import java.net.URI;

import org.personal.salportfoliobackend.domain.User;
import org.personal.salportfoliobackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/users")
public class UserAdminController {
    
    @Autowired
    UserService userService;
    
    Logger logger = LoggerFactory.getLogger(UserAdminController.class);

    @PostMapping("/")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (userService.insert(user) == 1) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
   
            return ResponseEntity.created(uri).body(user); 
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, 
        @PathVariable String id) {
        
        if (userService.update(user) == 1) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
