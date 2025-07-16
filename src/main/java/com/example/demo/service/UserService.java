package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserWithPostsDTO;
import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User createUser(UserCreateDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return userDao.save(user);
    }

    public List<UserResponseDTO> fetchUser() {
        List<User> users = userDao.findAll();
        return UserMapper.toDTOList(users);
    }

    public User updateUserData(int id, UserCreateDTO userDTO) {
        User user = userDao.findById(id)
                        .orElseThrow(()-> new NotFoundException("User with id " + id + " not found"));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return userDao.save(user);
    }

    public User updatePartialUser(int id, Map<String, Object> updates) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new NotFoundException("User with " + id + " not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "username"-> user.setUsername(String.valueOf(value));
                case "email" -> user.setEmail(String.valueOf(value));
                case "password" -> user.setPassword(String.valueOf(value));
                default -> throw new NotFoundException("Filed " + key + " cannot be updated");
            }
        });

        return userDao.save(user);
    }

    public void deleteUser(int id) {
        userDao.deleteById(id);
    }

    public UserWithPostsDTO fetchUserWithPosts(int userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with " + userId + " not found"));

        return UserMapper.toUserWithPostDTO(user);
    }

}
