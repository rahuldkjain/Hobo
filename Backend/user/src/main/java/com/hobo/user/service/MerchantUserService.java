package com.hobo.user.service;

import com.hobo.user.exceptions.merchantuser.MerchantUserAlreadyExists;
import com.hobo.user.exceptions.merchantuser.MerchantUserNotFound;
import com.hobo.user.model.MerchantUserDTO;

public interface MerchantUserService {

    MerchantUserDTO getUser(String email) throws MerchantUserNotFound;

    MerchantUserDTO deleteUser(String email);

    MerchantUserDTO putUser(MerchantUserDTO userDTO) throws MerchantUserNotFound;

    MerchantUserDTO saveUser(MerchantUserDTO userDTO) throws MerchantUserAlreadyExists;

    MerchantUserDTO loginCheck(String email, String password);

}
