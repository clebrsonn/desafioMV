package com.api.mv.resource;

import com.api.mv.listener.EventListenerCreated;
import com.api.mv.model.User;
import com.api.mv.repository.UserRepository;
import com.api.mv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserRepository userRepository;

    private final UserService userService;

    private final ApplicationEventPublisher publisher;


    static Logger logger;

    @Autowired
    public UserResource(UserRepository userRepository, UserService userService, ApplicationEventPublisher publisher) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.publisher = publisher;
    }


    @GetMapping
    public List<User> users() {
        return userRepository.findAll();
    }


    @GetMapping("/filter")
    public Page<User> list(@RequestParam(required = false) Map<String, String> filters,
                           @RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "10") Integer size) {
        return userService.list(filters, new PageRequest(page, size));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user, HttpServletResponse response) {
        User userSaved = userRepository.save(user);
        publisher.publishEvent(new EventListenerCreated(this, response, userSaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> searchUser(@PathVariable Long id) {

        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        User userSaved = userService.update(id, user);
        return ResponseEntity.ok(userSaved);
    }
}
