package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(int age);
    List<User> findByUUID(String UUID);
    List<User> findByEmailAndPassword(String email, String password);
    List<User> getAllByEmail(String email);
    User getByEmail(String email);
}
