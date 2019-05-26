package com.hobo.user.service.serviceImpl;

import com.hobo.user.entity.MerchantUserEntity;
import com.hobo.user.exceptions.merchantuser.MerchantUserAlreadyExists;
import com.hobo.user.exceptions.merchantuser.MerchantUserNotFound;
import com.hobo.user.model.MerchantUserDTO;
import com.hobo.user.repository.MerchantUserRepository;
import com.hobo.user.service.MerchantUserService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class MerchantUserServiceImpl implements MerchantUserService {

    @Autowired
    private MerchantUserRepository merchantUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MerchantUserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public MerchantUserDTO getUser(String email) throws MerchantUserNotFound {
        if(!merchantUserRepository.existsByEmailId(email)) {
            throw new MerchantUserNotFound("Data not found");
        }
        MerchantUserEntity result = merchantUserRepository.findByEmailId(email);
        MerchantUserDTO resultDTO = new MerchantUserDTO();
        BeanUtils.copyProperties(result, resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public MerchantUserDTO deleteUser(String email) {
        MerchantUserEntity result = merchantUserRepository.findByEmailId(email);
        merchantUserRepository.delete(result.getMerchantId());
        MerchantUserDTO resultDTO = new MerchantUserDTO();
        BeanUtils.copyProperties(result, resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public MerchantUserDTO putUser(MerchantUserDTO merchantUserDTO) throws MerchantUserNotFound {
        if(!merchantUserRepository.existsByEmailId(merchantUserDTO.getEmailId())) {
            throw new MerchantUserNotFound("Data not found");
        }
        MerchantUserEntity user = new MerchantUserEntity();
        BeanUtils.copyProperties(merchantUserDTO, user);
        MerchantUserEntity result = merchantUserRepository.save(user);
        MerchantUserDTO resultDTO= new MerchantUserDTO();
        BeanUtils.copyProperties(result,resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public MerchantUserDTO saveUser(MerchantUserDTO merchantUserDTO) throws MerchantUserAlreadyExists {
        if(merchantUserRepository.existsByEmailId(merchantUserDTO.getEmailId())) {
            throw new MerchantUserAlreadyExists("Data already exists");
        }
        MerchantUserEntity user = new MerchantUserEntity();
        BeanUtils.copyProperties(merchantUserDTO, user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        MerchantUserEntity result = merchantUserRepository.save(user);
        addToMerchantdb(result);
        MerchantUserDTO resultDTO = new MerchantUserDTO();
        BeanUtils.copyProperties(result,resultDTO);
        resultDTO.setPassword("");
        return resultDTO;
    }

    @Override
    public boolean addToMerchantdb(MerchantUserEntity merchantUserEntity) {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject data = new JSONObject();
        data.put("merchantId",merchantUserEntity.getMerchantId());
        data.put("merchantName",merchantUserEntity.getName());
        data.put("address",merchantUserEntity.getAddress());
        data.put("email",merchantUserEntity.getEmailId());
        data.put("phoneNumber",merchantUserEntity.getPhoneNumber());
        final String url = "http://localhost:8083/merchant";
        HttpEntity<JSONObject> payload = new HttpEntity<>(data);
        URI location = restTemplate.postForLocation(url, payload);
        //assertThat(location, notNullValue());
        return false;
    }

    @Override
    public MerchantUserDTO loginCheck(String email, String password) {
        MerchantUserDTO merchantUserDTO= new MerchantUserDTO();
        if(merchantUserRepository.existsByEmailId(email)) {
            MerchantUserEntity result = merchantUserRepository.findByEmailId(email);
            if (bCryptPasswordEncoder.matches(password,result.getPassword())) {
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
