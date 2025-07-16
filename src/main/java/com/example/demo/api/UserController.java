package com.example.demo.api;

import com.example.demo.dto.UserWithPostsDTO;
import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import static com.example.demo.mapper.UserMapper.mapToResponseDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // Создает нового юзера
    @PostMapping("/users")
    public UserResponseDTO createUser(@Valid @RequestBody UserCreateDTO userDTO) {
        User user = userService.createUser(userDTO);

        return mapToResponseDTO(user);
    }

    // Получает всез юзеров
    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.fetchUser();
    }

    // Обновляет все данные по id
    @PutMapping("/{userId}")
    public UserResponseDTO updateUserDataById(@Valid @PathVariable int userId, @RequestBody UserCreateDTO userDTO) {
        User user = userService.updateUserData(userId, userDTO);

        return mapToResponseDTO(user);
    }

    // Обновляет данные юзера выборочно(пароль, ник отдельно)
    @PatchMapping("/{userId}")
    public UserResponseDTO updatePartialUser(@PathVariable int userId, @RequestBody Map<String, Object> updates) {
        User user = userService.updatePartialUser(userId, updates);
        return UserMapper.toDTO(user);
    }

    // Удаляет юзера по id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("{userId}/posts")
    public UserWithPostsDTO getAllPosts(@PathVariable int userId) {
        return userService.fetchUserWithPosts(userId);
    }

}
