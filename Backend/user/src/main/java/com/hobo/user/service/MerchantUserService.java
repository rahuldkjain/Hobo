package com.hobo.user.service;

import com.hobo.user.entity.MerchantUserEntity;
import com.hobo.user.exceptions.merchantuser.MerchantUserAlreadyExists;
import com.hobo.user.exceptions.merchantuser.MerchantUserNotFound;
import com.hobo.user.model.MerchantProfileDTO;
import com.hobo.user.model.MerchantUserDTO;

public interface MerchantUserService {

    MerchantUserDTO getUser(String email) throws MerchantUserNotFound;

    MerchantUserDTO deleteUser(String email);

    MerchantProfileDTO putUser(MerchantProfileDTO merchantProfileDTO) throws MerchantUserNotFound;

    MerchantUserDTO saveUser(MerchantUserDTO userDTO) throws MerchantUserAlreadyExists;

    MerchantUserDTO loginCheck(String email, String password);

    boolean addToMerchantdb(MerchantUserEntity merchantUserEntity);

}
