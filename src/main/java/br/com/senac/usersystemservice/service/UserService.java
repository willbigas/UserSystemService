package br.com.senac.usersystemservice.service;

import br.com.senac.usersystemservice.model.User;
import br.com.senac.usersystemservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllActives() {
        return userRepository.findAllByActiveTrue();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long userId) {
        User user = findById(userId);
        user.setActive(false);
        userRepository.save(user);
    }

    public boolean ifExists(Long id) {
        return userRepository.existsById(id);
    }

}
