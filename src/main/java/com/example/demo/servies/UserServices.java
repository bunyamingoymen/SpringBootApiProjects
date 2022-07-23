package com.example.demo.servies;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void createUser(User toAdd){
        userRepository.save(toAdd);
        System.out.println("Added: " + toAdd.getId());
    }

    public final User findById(Long id){
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User Not Found"));
    }

    public void delete(Long id){
        userRepository.deleteById(id);
        System.out.println("User Deleted: " + id);
    }


    @Transactional
    public void update(User user, Long id){

        User usertoUpdate = this.userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User Not Found"));

        if(Objects.nonNull(user.getName()) && !user.getName().equals("")){
            usertoUpdate.setName(user.getName());
        }

        if(Objects.nonNull(user.getSurname()) && !user.getSurname().equals("")){
            usertoUpdate.setSurname(user.getSurname());
        }

    }
}
