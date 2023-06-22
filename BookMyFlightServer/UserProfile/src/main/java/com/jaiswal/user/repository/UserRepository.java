package com.jaiswal.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.user.bean.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);

}
