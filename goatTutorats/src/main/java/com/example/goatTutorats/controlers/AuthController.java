package com.example.goatTutorats.controlers;

import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.services.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
    @RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/get-login-page")
    public String getLoginPage(Model model) {
        // create empty tutor to store credential information
        Tutor tutor = new Tutor();

        model.addAttribute("tutor", tutor);
        return "login";
    }


        @PostMapping("/authenticate-tutor")
    public String authenticateTutor(@ModelAttribute Tutor tutor) {
        // check tutor exists in db and password is correct

        return "redirect:/dashboard";
    }
}
