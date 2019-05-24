package com.hobo.user.service.serviceImpl;

import com.hobo.user.entity.UserEntity;
import com.hobo.user.model.UserDTO;
import com.hobo.user.repository.UserRepository;
import com.hobo.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUser(Integer id) {
        UserEntity result = userRepository.findOne(id);
        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(result, resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public UserDTO deleteUser(Integer id) {
        UserEntity result = userRepository.findOne(id);
        userRepository.delete(id);
        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(result, resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public UserDTO putUser(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        UserEntity result = userRepository.save(user);
        UserDTO resultDTO= new UserDTO();
        BeanUtils.copyProperties(result,resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        if( userRepository.existsByEmailId(user.getEmailId())) {
            throw new RuntimeException();
        }
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
            if (password.equals(result.getPassword())) {
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
