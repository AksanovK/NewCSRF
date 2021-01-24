package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    List<User> getAllByUUID(String uuid);
    List<User> getAllByEmailAndPassword(String email, String password);
    List<User> getAllByEmail(String email);
    User getByEmail(String email);
    void addUser(User user);
}
