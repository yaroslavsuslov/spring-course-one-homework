package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persistence.entity.User;
import ru.geekbrains.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllUsers(Model model) {
         model.addAttribute("users", userService.getAllUsersWithRoles());
         return "users";
    }
}
