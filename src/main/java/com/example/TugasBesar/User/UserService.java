package com.example.TugasBesar.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

     @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Enkripsi password
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public boolean checkUsername(String username){
        if(!userRepository.findByUsername(username).isEmpty()){
            return true;
        } else{
            return false;
        }
    }

    public User login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Membandingkan password yang dimasukkan dengan password terenkripsi di basis data
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}

