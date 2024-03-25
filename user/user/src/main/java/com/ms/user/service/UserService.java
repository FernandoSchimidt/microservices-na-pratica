package com.ms.user.service;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository repository;
    final UserProducer userProducer;

    public UserService(UserRepository repository, UserProducer userProducer) {
        this.repository = repository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel user) {
        user = repository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }

    public List<UserModel> getUsers() {
        return repository.findAll();
    }

    public Optional<UserModel> getByUuid(UUID uuid) {
        return repository.findById(uuid);
    }

    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    public UserModel update(UserModel user) {
        var userOld = repository.findById(user.getUserId());

        if (userOld.isPresent()) {
            user = repository.save(user);
        }
        return user;
    }


}
