package by.zhigalko.snowworld.controller;

import by.zhigalko.snowworld.dto.CartDto;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.dto.request.UserRequest;
import by.zhigalko.snowworld.dto.response.UserResponse;
import by.zhigalko.snowworld.service.CartService;
import by.zhigalko.snowworld.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Set;

@Slf4j
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

    @PostMapping("/login")
    public String handleLoginFailure(Model model) {
        model.addAttribute("error", "Пользователь с таким именем и паролем не существует");
        return "login";
    }

    @GetMapping("/welcome")
    public String showWelcomePage(Authentication authentication, Model model) {
        UserResponse userResponse = userService.findByUsername(authentication.getName());
        model.addAttribute("user", userResponse);
        CartDto foundCart = cartService.findCartById(userResponse.getId());
        model.addAttribute("cart", foundCart);
        if(foundCart!=null) {
            Set<ItemResponse> cartItems = cartService.getItemsFromCart(foundCart.getId());
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalSum", foundCart.getTotalSum());
        }
        model.addAttribute("ROLE", authentication.getAuthorities().stream().findFirst().orElseThrow());
        return "welcome";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@Valid @ModelAttribute("userRequest") UserRequest userRequest,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().toString());
            return "registration";
        }
        userService.createUser(userRequest);
        return "login";
    }
}
