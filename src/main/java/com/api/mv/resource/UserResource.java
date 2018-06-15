package com.api.mv.resource;

import com.api.mv.model.User;
import com.api.mv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserRepository userRepository;
    static Logger logger;

    @Autowired
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> users() {
        return userRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user, HttpServletResponse response) {
        User userSaved = userRepository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(userSaved.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());
        return ResponseEntity.created(uri).body(userSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> searchUser(@PathVariable Long id) {

        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
