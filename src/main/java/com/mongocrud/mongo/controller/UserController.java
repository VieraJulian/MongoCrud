package com.mongocrud.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongocrud.mongo.documents.User;
import com.mongocrud.mongo.repository.IUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepo;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            User userCreated = userRepo.save(user);
            return new ResponseEntity<User>(userCreated, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable String id) {
        try {
            User user = userRepo.findById(id).orElse(null);
            return new ResponseEntity<User>(user, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllUser() {
        try {
            List<User> users = userRepo.findAll();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User userInfo) {
        try {
            User user = userRepo.findById(id).orElse(null);

            user.setName(userInfo.getName());
            user.setEmail(userInfo.getEmail());

            User userUpdated = userRepo.save(user);

            return new ResponseEntity<User>(userUpdated, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try {
            userRepo.deleteById(id);
            return new ResponseEntity<String>("User deleted.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
