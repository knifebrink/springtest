package com.spring.test.chapter6.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// JPA也很有意思
@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser,Long> {

    List<MongoUser> findByUserNameLike(String userName);
}
