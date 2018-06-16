package com.api.mv.service;

import com.api.mv.model.User;
import com.api.mv.repository.UserRepository;
import com.api.mv.repository.especification.UserEspecification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Page<User> list(Map<String, String> filters, org.springframework.data.domain.Pageable pageable) {
        return userRepository.findAll(UserEspecification.filterWithOptions(filters), pageable);
    }



    public User update(Long id, User user) {
        Optional<User> savedUser = userRepository.findById(id);

//        savedUser.ifPresent(user1 -> {
//            BeanUtils.copyProperties(user, user1, "id");
//            userRepository.save(user1);
//
//        });

        if (savedUser.isPresent()) {

            //BeanUtils vários métodos úteis, copyproperties serve para copiar os atributos de uma classe para outro
            BeanUtils.copyProperties(user, savedUser.get(), "id");
            return userRepository.save(savedUser.get());
        } else {
            throw new EmptyResultDataAccessException(1);
        }

    }


}
