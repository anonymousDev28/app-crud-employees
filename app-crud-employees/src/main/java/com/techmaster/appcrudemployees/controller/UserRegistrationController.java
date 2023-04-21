package com.techmaster.appcrudemployees.controller;

import com.techmaster.appcrudemployees.dto.UserRegistrationDTO;
import com.techmaster.appcrudemployees.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserService userService;
    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO(){
        return new UserRegistrationDTO();
    }
    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("user",new UserRegistrationDTO());
        return "registration";
    }
    @PostMapping
    public String registerUserAcc(@ModelAttribute("user")UserRegistrationDTO dto){
        userService.save(dto);
        return "redirect:/registration?success";
    }
}
