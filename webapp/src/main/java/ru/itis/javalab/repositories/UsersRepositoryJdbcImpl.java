package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.RowMapper;
import ru.itis.javalab.repositories.SimpleJdbcTemplate;
import ru.itis.javalab.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;


public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_BY_EMAIL = "select 1 from users where email = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_EMAIL = "select * from users where email = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_UUID= "select * from users where UUID = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from users";

    //language=SQL
    private static final String SQL_FIND_BY_EMAIL_AND_PASS = "select * from users where email = ? and password = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = ?";

    //language=SQL
    public static final String SQL_SAVE_USER="insert into users (email, password, uuid) values (?, ?, ?);";

    private DataSource dataSource;

    Connection connection;

    SimpleJdbcTemplate simpleJdbcTemplate;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = this.dataSource.getConnection();
        } catch (SQLException throwables) {
            throw new IllegalArgumentException(throwables);
        }
        simpleJdbcTemplate = new SimpleJdbcTemplate(connection);
    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .UUID(row.getString("UUID"))
            .password(row.getString("password"))
            .email(row.getString("email"))
            .build();

    @Override
    public void save(User entity) {
        simpleJdbcTemplate.isQuery(SQL_SAVE_USER, entity.getEmail(), entity.getPassword(), entity.getUUID());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return simpleJdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public List<User> findAllByAge(int age) {
        return simpleJdbcTemplate.query(SQL_FIND_ALL_BY_AGE, userRowMapper, age);
    }

    @Override
    public List<User> findByUUID(String UUID) {
        return simpleJdbcTemplate.query(SQL_FIND_ALL_BY_UUID, userRowMapper, UUID);
    }

    @Override
    public List<User> findByEmailAndPassword(String email, String password) {
        return simpleJdbcTemplate.query(SQL_FIND_BY_EMAIL_AND_PASS, userRowMapper, email, password);
    }

    @Override
    public List<User> getAllByEmail(String email) {
        return simpleJdbcTemplate.query(SQL_FIND_ALL_BY_EMAIL, userRowMapper, email);
    }

    @Override
    public User getByEmail(String email) {
        return simpleJdbcTemplate.query(SQL_FIND_BY_EMAIL,userRowMapper, email).get(0);
    }

}
