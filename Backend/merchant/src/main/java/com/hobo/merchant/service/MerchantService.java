package com.hobo.merchant.service;

import com.hobo.merchant.Exceptions.MerchantExceptions.MerchantAlreadyExists;
import com.hobo.merchant.Exceptions.MerchantExceptions.MerchantNotFound;
import com.hobo.merchant.model.MerchantDTO;

public interface MerchantService {
    MerchantDTO createMerchant(MerchantDTO merchantDTO) throws MerchantAlreadyExists;
    MerchantDTO readMerchantById(Integer merchantId) throws MerchantNotFound;
    MerchantDTO updateMerchant(MerchantDTO merchantDTO) throws MerchantNotFound;
    MerchantDTO deleteMerchantById(Integer merchantId) throws MerchantNotFound;

    MerchantDTO updateMerchantRating(Integer merchantId, float merchantRating)throws MerchantNotFound;
}
