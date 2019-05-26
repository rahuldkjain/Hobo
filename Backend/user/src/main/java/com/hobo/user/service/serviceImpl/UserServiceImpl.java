package com.hobo.user.service.serviceImpl;

import com.hobo.user.entity.UserEntity;
import com.hobo.user.exceptions.user.UserAlreadyExists;
import com.hobo.user.exceptions.user.UserNotFound;
import com.hobo.user.model.UserDTO;
import com.hobo.user.repository.UserRepository;
import com.hobo.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public UserDTO deleteUser(String email) {
        UserEntity result = userRepository.findByEmailId(email);
        userRepository.delete(result.getUserId());
        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(result, resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public UserDTO putUser(UserDTO userDTO) throws UserNotFound {
        if(!userRepository.existsByEmailId(userDTO.getEmailId())) {
            throw new UserNotFound("Data not found");
        }
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        UserEntity result = userRepository.save(user);
        UserDTO resultDTO= new UserDTO();
        BeanUtils.copyProperties(result,resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
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
