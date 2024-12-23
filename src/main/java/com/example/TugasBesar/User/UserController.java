package com.example.TugasBesar.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerView(User user){
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        
        // Validasi tambahan untuk password dan confirm password
        if (!user.getPassword().equals(user.getConfirmpassword())) {
            bindingResult.rejectValue(
                "confirmpassword",
                "PasswordMismatch",
                "Passwords do not match"
                );
            }
            
        if (userService.checkUsername(user.getUsername())) {
            bindingResult.rejectValue(
                "username", 
                "DuplicateUsername", 
                "Username already exists."
            );
        }

        if (bindingResult.hasErrors()) {
            return "register"; // Jika validasi gagal, kembali ke halaman register
        }

        try {
            userService.register(user);
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during registration: " + e.getMessage());
            return "register";
        }

        return "redirect:/results";
    }


    @GetMapping("/results")
    public String resultsView(){
        return "member/home-member";
    }
}
