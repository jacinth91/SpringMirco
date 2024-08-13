package com.jacinth91.rest.webservices.restfull_web_services.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service = service;
    }
    @GetMapping("/users/{id}")
   public User retireveALLUser(@PathVariable int id){

       User user  = service.findOne(id);
       if (user == null){
           throw new UserNotFoundException("id:"+id);
       }
       return user;

   }
   @GetMapping("/users")
   public List<User> allUsers(){
        return service.findAll();
   }
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){
       User savedUser =  service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping ("/users/{id}")
    public void deleteUser(@PathVariable int id){
    service.deleteUserById(id);
    }
}
