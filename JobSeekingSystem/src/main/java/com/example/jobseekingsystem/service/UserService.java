package com.example.jobseekingsystem.service;

import com.example.jobseekingsystem.modle.User;
import com.example.jobseekingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addnewUser(User user){
        userRepository.save(user);

    }

    public boolean updateUser(Integer id , User user){
        User olduser=userRepository.findById(id).orElse(null);
        if(olduser == null){
            return false;
        }
        olduser.setName(user.getName());
        olduser.setAge(user.getAge());
        olduser.setEmail(user.getEmail());
        olduser.setPassword(user.getPassword());
        olduser.setRole(user.getRole());
        userRepository.save(olduser);
        return true;
    }

    public Boolean deleteUser(Integer id ){
        User delted=userRepository.findById(id).orElse(null);
        if(delted == null){
            return false;
        }
        userRepository.delete(delted);
        return true;
    }
}
