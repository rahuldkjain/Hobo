package com.hobo.merchant.service;

import com.hobo.merchant.exceptions.merchantexceptions.MerchantAlreadyExists;
import com.hobo.merchant.exceptions.merchantexceptions.MerchantNotFound;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.model.NameAndStockDTO;

public interface MerchantService {
    MerchantDTO createMerchant(MerchantDTO merchantDTO) throws MerchantAlreadyExists;
    MerchantDTO readMerchantById(Integer merchantId) throws MerchantNotFound;
    MerchantDTO updateMerchant(MerchantDTO merchantDTO) throws MerchantNotFound;
    MerchantDTO deleteMerchantById(Integer merchantId) throws MerchantNotFound;

    MerchantDTO updateMerchantRating(Integer merchantId, float merchantRating)throws MerchantNotFound;

    String getName(Integer merchantId);

    NameAndStockDTO getNameAndStock(Integer merchantId, Integer productId);
}
