package io.github.yhugorocha.service;

import io.github.yhugorocha.entity.UserEntity;
import io.github.yhugorocha.exception.UserNotFoundException;
import io.github.yhugorocha.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity){
        userRepository.persist(userEntity);
        return userEntity;
    }

    public List<UserEntity> findAll(Integer page, Integer size) {
        return userRepository.findAll().page(page, size).list();
    }

    public UserEntity findById(UUID id) {
        return (UserEntity) userRepository.findByIdOptional(id).orElseThrow(UserNotFoundException::new);
    }

    public UserEntity updateUser(UUID id, UserEntity userEntity) {
        UserEntity user = findById(id);
        user.setUsername(userEntity.getUsername());
        userRepository.persist(user);
        return user;
    }

    public void delete(UUID id) {
        UserEntity user = findById(id);
        userRepository.deleteById(user.getId());
    }
}
