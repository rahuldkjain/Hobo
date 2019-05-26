package com.hobo.user.controller;


import com.hobo.user.exceptions.user.UserAlreadyExists;
import com.hobo.user.exceptions.user.UserNotFound;
import com.hobo.user.model.UserDTO;
import com.hobo.user.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public JSONObject getUser (@RequestParam String emailId) throws UserNotFound {
        UserDTO userDTO = userService.getUser(emailId);
        JSONObject response = getJSONResponse(userDTO);
        response.replace("message", "success", "fetching successful");
        return response;
    }

    @PostMapping(value="/user", consumes = {"application/json"})
    public JSONObject saveUser(@RequestBody UserDTO userDTO) throws UserAlreadyExists {
        UserDTO result = userService.saveUser(userDTO);
        JSONObject response = getJSONResponse(result);
        response.replace("message", "success", "adding successful");
        return response;
    }

    @PutMapping(value="/user", consumes = {"application/json"})
    public JSONObject updateUser(@RequestBody UserDTO userDTO) throws UserNotFound {
        UserDTO result = userService.putUser(userDTO);
        JSONObject response = getJSONResponse(result);
        response.replace("message", "success", "updating successful");
        return response;
    }

    @DeleteMapping("/user")
    public JSONObject deleteUser(@RequestParam String emailId) {
        UserDTO userDTO = userService.deleteUser(emailId);
        JSONObject response = getJSONResponse(userDTO);
        response.replace("message", "success", "deleting successful");
        return response;
    }

    @PostMapping(value="/login", consumes = {"application/json"})
    public JSONObject loginCheck(@RequestBody Login data) {
        UserDTO userDTO = userService.loginCheck(data.getEmailId(),data.getPassword());
        JSONObject response = getJSONResponse(userDTO);
        if(userDTO== null)
            response.replace("message", "success", "verification fail");
        else
            response.replace("message", "success", "verification successful");
        return response;
    }


}

class Login {
    private String emailId;
    private String password;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
