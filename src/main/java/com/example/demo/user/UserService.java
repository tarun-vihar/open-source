package com.example.demo.user;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final  UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> filterUserByTag(List<User> users, String tagValue){

//        !Arrays.asList(user.getTags()).contains(tagValue)
        List<User> nonTagedList = users
                .stream()
                .filter( user -> !isContainsTag(user.getTags(), tagValue))
                // isContains is the helper function to check if the flagged string is user tags of Not
                .collect(Collectors.toList());

        return  nonTagedList;
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    // Seraches if the flagged tag(disabled) is present in the Users tags
    public boolean isContainsTag(String [] tags, String tagValue){
        for(String tag: tags)
            if(tag.equalsIgnoreCase(tagValue)) return  true;
        return false;
    }

}
