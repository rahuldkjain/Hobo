package com.hobo.user.service.serviceImpl;

import com.hobo.user.entity.MerchantUserEntity;
import com.hobo.user.model.MerchantUserDTO;
import com.hobo.user.repository.MerchantUserRepository;
import com.hobo.user.service.MerchantUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantUserServiceImpl implements MerchantUserService {

    @Autowired
    private MerchantUserRepository merchantUserRepository;

    @Override
    public MerchantUserDTO getUser(Integer id) {
        MerchantUserEntity result = merchantUserRepository.findOne(id);
        MerchantUserDTO resultDTO = new MerchantUserDTO();
        BeanUtils.copyProperties(result, resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public MerchantUserDTO deleteUser(Integer id) {
        MerchantUserEntity result = merchantUserRepository.findOne(id);
        merchantUserRepository.delete(id);
        MerchantUserDTO resultDTO = new MerchantUserDTO();
        BeanUtils.copyProperties(result, resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public MerchantUserDTO putUser(MerchantUserDTO merchantUserDTO) {
        MerchantUserEntity user = new MerchantUserEntity();
        BeanUtils.copyProperties(merchantUserDTO, user);
        MerchantUserEntity result = merchantUserRepository.save(user);
        MerchantUserDTO resultDTO= new MerchantUserDTO();
        BeanUtils.copyProperties(result,resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public MerchantUserDTO saveUser(MerchantUserDTO merchantUserDTO) {
        MerchantUserEntity user = new MerchantUserEntity();
        BeanUtils.copyProperties(merchantUserDTO, user);
        if( merchantUserRepository.existsByEmailId(user.getEmailId())) {
            throw new RuntimeException();
        }
        MerchantUserEntity result = merchantUserRepository.save(user);
        MerchantUserDTO resultDTO = new MerchantUserDTO();
        BeanUtils.copyProperties(result,resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public MerchantUserDTO loginCheck(String email, String password) {
        MerchantUserDTO merchantUserDTO= new MerchantUserDTO();
        if(merchantUserRepository.existsByEmailId(email)) {
            MerchantUserEntity result = merchantUserRepository.findByEmailId(email);
            if (password.equals(result.getPassword())) {
                BeanUtils.copyProperties(result, merchantUserDTO);
                merchantUserDTO.setPassword("");
            }
            else {
                merchantUserDTO= null;
            }
        }
        else {
            merchantUserDTO= null;
        }
        return merchantUserDTO;
    }
}
