package com.spring.test.chapter6.chapter6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author brink
 * 2021/7/6 16:07
 */
@Service
public class UserBatch {
    @Autowired
    UserService userService;

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertUsers(List<User> users){
        int count = 0 ;
        for (User user : users) {
            count += userService.insertUser(user);
        }
        return count;
    }
}
