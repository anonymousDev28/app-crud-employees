package com.techmaster.appcrudemployees.service;

import com.techmaster.appcrudemployees.dto.UserRegistrationDTO;
import com.techmaster.appcrudemployees.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User save(UserRegistrationDTO dto);
}
