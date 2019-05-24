package com.hobo.user.controller;


import com.hobo.user.model.UserDTO;
import com.hobo.user.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");

        response.remove(data,"password");

        return response;
    }

    @GetMapping("/user")
    public JSONObject getUser (@RequestParam Integer id) {
        UserDTO userDTO = userService.getUser(id);
        JSONObject response = getJSONResponse(userDTO);
        response.replace("message", "success", "fetching successful");
        return response;
    }

    @PostMapping(value="/user", consumes = {"application/json"})
    public JSONObject saveUser(@RequestBody UserDTO userDTO) {
        UserDTO result = userService.saveUser(userDTO);
        JSONObject response = getJSONResponse(result);
        response.replace("message", "success", "adding successful");
        return response;
    }

    @PutMapping(value="/user", consumes = {"application/json"})
    public JSONObject updateUser(@RequestBody UserDTO userDTO) {
        UserDTO result = userService.putUser(userDTO);
        JSONObject response = getJSONResponse(result);
        response.replace("message", "success", "updating successful");
        return response;
    }

    @DeleteMapping("/user")
    public JSONObject updateUser(@RequestParam Integer id) {
        UserDTO userDTO = userService.deleteUser(id);
        JSONObject response = getJSONResponse(userDTO);
        response.replace("message", "success", "deleting successful");
        return response;
    }

    @PostMapping(value="/login", consumes = {"application/json"})
    public JSONObject loginCheck(@RequestBody login data) {
        UserDTO userDTO = userService.loginCheck(data.getEmail(),data.getPassword());
        JSONObject response = getJSONResponse(userDTO);
        response.replace("message", "success", "verification successful");
        return response;
    }


}

class login {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
