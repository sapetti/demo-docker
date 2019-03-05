package com.softtek.demodocker;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByName(String name);
	void deleteByName(String name);
}
