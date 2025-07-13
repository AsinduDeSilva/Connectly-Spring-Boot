package com.example.demo.controller;

import com.example.demo.service.AuthService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    private final UserService userService;
    private final PostService postService;
    private final AuthService authService;

    public HomePageController(UserService userService, PostService postService, AuthService authService) {
        this.userService = userService;
        this.postService = postService;
        this.authService = authService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("posts",postService.getFeedPosts(authService.getLoggedUser().getUserId()));
        return "home";
    }
}
