package com.mongocrud.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongocrud.mongo.documents.User;

public interface IUserRepository extends MongoRepository<User, String> {

}
