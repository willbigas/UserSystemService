package br.com.senac.usersystemservice.service;

import br.com.senac.usersystemservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    @Autowired
    private UserRepository userRepository;

    public void login() {

    }


    public void logout() {

    }
}