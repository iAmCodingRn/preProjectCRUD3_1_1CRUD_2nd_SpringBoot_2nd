package web.ProjectBoot.service;

import web.ProjectBoot.model.User;


import java.util.List;


public interface UserService {

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User findUserById(long id);
}