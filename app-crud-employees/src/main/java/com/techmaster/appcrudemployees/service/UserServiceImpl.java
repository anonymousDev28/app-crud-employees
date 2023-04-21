package com.techmaster.appcrudemployees.service;

import com.techmaster.appcrudemployees.dto.UserRegistrationDTO;
import com.techmaster.appcrudemployees.model.Role;
import com.techmaster.appcrudemployees.model.User;
import com.techmaster.appcrudemployees.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserRegistrationDTO dto) {
        return userRepository.save(new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),passwordEncoder.encode(dto.getPassword()), List.of(new Role("ROLE_USER"))));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesGrantedAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesGrantedAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
