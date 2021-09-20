package com.example.listingapp.servicies;

import com.example.listingapp.model.User;
import com.example.listingapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        if (user == null) {
            return null;
        }
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            return null;
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isEmpty()) {
            return null;
        }
        return userRepository.save(user);
    }

    public boolean deleteById(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    public User findByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
            return null;
        }
        return byEmail.get();
    }
}
