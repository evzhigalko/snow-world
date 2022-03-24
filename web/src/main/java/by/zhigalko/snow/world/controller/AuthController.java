package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.dto.UserDTO;
import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@SessionAttributes({"user", "ROLE"})
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String handleLogin() {
        return "login";
    }

    @GetMapping("/welcome")
    public String showWelcomePage(Authentication authentication, Model model) {
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        model.addAttribute("ROLE", authentication.getAuthorities().stream().findFirst().orElseThrow());
        return "welcome";
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(UserDTO userDTO, Model model){
        try {
            userService.createUser(userDTO);
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return "registration";
        }
        return "login";
    }
}
