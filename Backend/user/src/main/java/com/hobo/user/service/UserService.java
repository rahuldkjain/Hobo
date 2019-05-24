package com.hobo.user.service;

import com.hobo.user.model.UserDTO;

public interface UserService {

    UserDTO getUser(Integer id);

    UserDTO deleteUser(Integer id);

    UserDTO putUser(UserDTO userDTO);

    UserDTO saveUser(UserDTO userDTO);

    UserDTO loginCheck(String email, String password);

}
