package com.api.mv.repository;

import com.api.mv.model.User;
import com.api.mv.repository.especification.UserEspecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Override
    Optional<User> findById(Long id);

}
