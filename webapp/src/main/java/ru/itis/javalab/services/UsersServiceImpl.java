package ru.itis.javalab.services;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getAllByUUID(String uuid) {
        return usersRepository.findByUUID(uuid);
    }

    @Override
    public List<User> getAllByEmailAndPassword(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<User> getAllByEmail(String email) {
        return usersRepository.getAllByEmail(email);
    }

    @Override
    public User getByEmail(String email) {
        return usersRepository.getByEmail(email);
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }
}
