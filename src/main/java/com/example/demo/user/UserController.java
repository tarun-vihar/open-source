package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private  final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public Map<String, Object> getAllUsers(){
        Map<String, Object> json = new HashMap<String, Object>();
        List<User> resultant = userService.filterUserByTag(userService.getAllUsers(),"Disabled");
        json.put("users",resultant);
        return json;
    }

    @PostMapping("/users/add")
    public ResponseEntity insertUser(@RequestBody User user){

        ResponseEntity responseEntity = new ResponseEntity(userService.insertUser(user), HttpStatus.OK);
        return responseEntity;
    }
}
