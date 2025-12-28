package com.aiplus.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aiplus.user.dto.UserCreationDto;
import com.aiplus.user.dto.UserDetailDto;
import com.aiplus.user.dto.UsersDto;
import com.aiplus.user.exceptions.NotLoggedInException;
import com.aiplus.user.mapper.UserMapper;
import com.aiplus.user.model.User;
import com.aiplus.user.service.UserService;
import com.aiplus.user.utils.ApiResponse;
import com.aiplus.user.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsersDto>>> getAllUsers() {
        List<UsersDto> dtos = userService.getAllUsers().stream().map(userMapper::toDto).toList();
        return ResponseEntity.ok(ResponseUtil.success("Users fetched", dtos));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDetailDto>> getAuthenticatedUser(@AuthenticationPrincipal User user) {
        if (user == null) {
            throw new NotLoggedInException("You Are Not Logged In !");
        }
        UserDetailDto dto = userMapper.toDetailDto(userService.findById(user.getId()));
        return ResponseEntity.ok(ResponseUtil.success("User found", dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDetailDto>> getUserById(@PathVariable Long id) {
        UserDetailDto dto = userMapper.toDetailDto(userService.findById(id));
        return ResponseEntity.ok(ResponseUtil.success("User found", dto));
    }

    @GetMapping("/by-email")
    public ResponseEntity<ApiResponse<UserDetailDto>> getUserByEmail(@RequestParam String email) {
        UserDetailDto dto = userMapper.toDetailDto(userService.findByEmail(email));
        return ResponseEntity.ok(ResponseUtil.success("User found", dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDetailDto>> createUser(@Valid @RequestBody UserCreationDto creationDto) {
        UserDetailDto created = userMapper.toDetailDto(userService.saveUser(userMapper.toEntity(creationDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseUtil.success("User created", created));
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse<UserDetailDto>> updateUser(@AuthenticationPrincipal User user,
            @Valid @RequestBody UserDetailDto updateDto) {
        UserDetailDto updated = userMapper
                .toDetailDto(userService.updateUser(user.getId(), userMapper.toEntity(updateDto)));
        return ResponseEntity.ok(ResponseUtil.success("User updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ResponseUtil.success("User deleted", null));
    }
}