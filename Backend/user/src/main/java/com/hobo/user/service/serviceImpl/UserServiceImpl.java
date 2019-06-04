package com.hobo.user.service.serviceImpl;

import com.hobo.user.entity.UserEntity;
import com.hobo.user.exceptions.user.UserAlreadyExists;
import com.hobo.user.exceptions.user.UserNotFound;
import com.hobo.user.model.UserDTO;
import com.hobo.user.model.UserProfileDTO;
import com.hobo.user.repository.UserRepository;
import com.hobo.user.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO getUser(String email) throws UserNotFound {
        if(!userRepository.existsByEmailId(email)) {
            throw new UserNotFound("Data not found");
        }
        UserEntity result = userRepository.findByEmailId(email);
        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(result, resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    @Transactional
    public UserDTO deleteUser(String email) {
        UserEntity result = userRepository.findByEmailId(email);
        userRepository.delete(result.getUserId());
        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(result, resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    @Transactional
    public UserProfileDTO putUser(UserProfileDTO userProfileDTO) throws UserNotFound {
        UserEntity user = userRepository.findByEmailId(userProfileDTO.getEmailId());
        if(user == null) {
            throw new UserNotFound("Data not found");
        }
        UserEntity result = new UserEntity();
        BeanUtils.copyProperties(userProfileDTO, result);
        result.setUserId(user.getUserId());
        result = userRepository.save(result);
        UserProfileDTO resultDTO= new UserProfileDTO();
        BeanUtils.copyProperties(result,resultDTO);
        return resultDTO;
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO userDTO) throws UserAlreadyExists {
        if(userRepository.existsByEmailId(userDTO.getEmailId())){
            throw new UserAlreadyExists("Data already exists");
        }
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserEntity result = userRepository.save(user);
        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(result,resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public UserDTO loginCheck(String email, String password) {
        UserDTO userDTO = new UserDTO();
        if(userRepository.existsByEmailId(email)) {
            UserEntity result = userRepository.findByEmailId(email);
            if (bCryptPasswordEncoder.matches(password,result.getPassword())) {
                BeanUtils.copyProperties(result, userDTO);
                userDTO.setPassword("");
            }
            else {
                userDTO = null;
            }
        }
        else {
            userDTO = null;
        }
        return userDTO;
    }
}
