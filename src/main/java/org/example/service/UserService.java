package org.example.service;

import org.example.dto.UserRequest;
import org.example.entity.User;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setGender(userRequest.getGender());
        user.setMobile(userRequest.getMobile());
        user.setEmail(userRequest.getEmail());
        user.setAge(userRequest.getAge());
        user.setNationality(userRequest.getNationality());
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(int id) throws UserNotFoundException {
        User user = repository.findByUserId(id);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("user not found with id : "+id);
        }
    }

    public User updateUser(int id, UserRequest request) {
        try {
            User existingUser = getUser(id);
            existingUser.setName(request.getName());
            existingUser.setAge(request.getAge());
            existingUser.setEmail(request.getEmail());
            existingUser.setMobile(request.getMobile());
            existingUser.setGender(request.getGender());
            existingUser.setNationality(request.getNationality());
            return repository.save(existingUser);
        } catch (UserNotFoundException ex) {
            return null;
        }
    }

    public void deleteUser(int id) throws UserNotFoundException {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            throw new UserNotFoundException("user id not found"+id);
        }
    }

}
