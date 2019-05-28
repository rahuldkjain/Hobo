package com.hobo.user.service;

import com.hobo.user.exceptions.user.UserAlreadyExists;
import com.hobo.user.exceptions.user.UserNotFound;
import com.hobo.user.model.UserDTO;
import com.hobo.user.model.UserProfileDTO;

public interface UserService {

    UserDTO getUser(String email) throws UserNotFound;

    UserDTO deleteUser(String email);

    UserProfileDTO putUser(UserProfileDTO userProfileDTO) throws UserNotFound;

    UserDTO saveUser(UserDTO userDTO) throws UserAlreadyExists;

    UserDTO loginCheck(String email, String password);

}
