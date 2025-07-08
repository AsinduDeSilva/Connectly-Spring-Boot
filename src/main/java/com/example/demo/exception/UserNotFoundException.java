package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
      super("User with id " + userId + " not found");
    }
}
