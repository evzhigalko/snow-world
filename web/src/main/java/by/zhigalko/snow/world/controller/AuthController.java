package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.dto.CartDto;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.dto.user.UserResponse;
import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.service.cart.CartService;
import by.zhigalko.snow.world.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@Log4j2
@Controller
@SessionAttributes({"user", "ROLE", "cart", "cartItems", "totalSum"})
public class AuthController {
    private final UserService userService;
    private final CartService cartService;

    @Autowired
    public AuthController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/login")
    public String handleLogin() {
        return "login";
    }

    @GetMapping("/welcome")
    public String showWelcomePage(Authentication authentication, Model model) {
        UserResponse userResponse = userService.findByUsername(authentication.getName());
        model.addAttribute("user", userResponse);
        CartDto foundCart = cartService.findCartById(userResponse.getId());
        model.addAttribute("cart", foundCart);
        if(foundCart!=null) {
            Set<ItemResponse> cartItems = cartService.getItems(foundCart.getId());
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalSum", foundCart.getTotalSum());
        }
        model.addAttribute("ROLE", authentication.getAuthorities().stream().findFirst().orElseThrow());
        return "welcome";
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(UserRequest userRequest, Model model){
        try {
            userService.createUser(userRequest);
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return "registration";
        }
        return "login";
    }
}
