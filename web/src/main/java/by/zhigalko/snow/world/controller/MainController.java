package by.zhigalko.snow.world.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/snowboard")
    public String handleSnowboardGroup() {
        return "snowboard-group";
    }

    @GetMapping("/ski")
    public String handleSkiGroup() {
        return "ski-group";
    }

    @GetMapping("/clothes")
    public String handleClothesGroup() {
        return "clothes-group";
    }

    @GetMapping("/contacts")
    public String handleContacts() {
        return "static/contacts";
    }

    @GetMapping("/form/login")
    public String handleLogin() {
        return "login";
    }

    @GetMapping("/form/registration")
    public String handleRegistration() {
        return "registration";
    }

    @GetMapping("/admin/")
    public String handleAdministration() {
        return "administration/admin";
    }
}
