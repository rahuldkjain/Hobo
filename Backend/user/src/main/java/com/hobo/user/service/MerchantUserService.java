package com.hobo.user.service;

import com.hobo.user.model.MerchantUserDTO;

public interface MerchantUserService {

    MerchantUserDTO getUser(Integer id);

    MerchantUserDTO deleteUser(Integer id);

    MerchantUserDTO putUser(MerchantUserDTO userDTO);

    MerchantUserDTO saveUser(MerchantUserDTO userDTO);

    MerchantUserDTO loginCheck(String email, String password);

}
