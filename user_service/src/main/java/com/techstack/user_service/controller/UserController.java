package com.techstack.user_service.controller;

import com.techstack.user_service.entity.User;
import com.techstack.user_service.exception.UserNotFoundException;
import com.techstack.user_service.repository.UserRepository;
import com.techstack.user_service.service.UserModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserModelAssembler assembler;
    
    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> getAll() {
        List<EntityModel<User>> users = userRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(users, linkTo(methodOn(this.getClass()).getAll()).withSelfRel());
    }
    
    @GetMapping("/user/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(assembler::toModel)
                .orElseThrow(() -> new UserNotFoundException(id));
        
    }
    
    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> entityModel = assembler.toModel(userRepository.save(newUser));
        
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    
    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
