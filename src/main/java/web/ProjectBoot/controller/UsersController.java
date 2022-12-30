package web.ProjectBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.ProjectBoot.model.User;
import web.ProjectBoot.service.UserService;


import java.util.List;


@Controller
@RequestMapping("/users")
public class UsersController {

    UserService userService;

    @Autowired
    protected UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(ModelMap model) {
        List<User> usersList = userService.getAllUsers();
        model.addAttribute("users", usersList);
        return "users";
    }

    @GetMapping("search")

    public String getUserById(@PathVariable(value = "id", required = false) long id,
                            ModelMap model) {
        model.addAttribute("users", (userService.findUserById(id)));
        return "users";
    }

    @GetMapping("new")
    public String newUserForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editUserForm(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.findUserById(id));
        return "edit_user";
    }

    @PostMapping("edit")
    public String updateUserInfo(@ModelAttribute("user") User user) {

        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("delete/{id}")
    public String deleteUserById(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}