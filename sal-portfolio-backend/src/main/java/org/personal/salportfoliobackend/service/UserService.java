package org.personal.salportfoliobackend.service;

import java.util.List;
import java.util.Optional;

import org.personal.salportfoliobackend.dao.UserDao;
import org.personal.salportfoliobackend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService extends CommonServiceImpl<User, UserDao> 
    implements CommonService<User> {

    @Autowired
    UserDao userDao;
    
    /**
     * Fetches the User record with a matching first and last name.
     * 
     * @param firstName The first name to match.
     * @param lastName The last name to match.
     * @return Any User that matches both first and last names.
     */
    Optional<List<User>> getByFirstAndLastName(String firstName, 
        String lastName) {
        
        if (firstName.isEmpty() || lastName.isEmpty()) {
            return Optional.ofNullable(null);
        }
        
        return userDao.getByFirstAndLastName(firstName, lastName);
    }
}
