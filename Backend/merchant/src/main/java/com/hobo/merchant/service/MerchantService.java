package com.hobo.merchant.service;

import com.hobo.merchant.entity.JoinedTable;
import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.model.MerchantDTO;

import java.util.List;

public interface MerchantService {
    MerchantDTO createMerchant(MerchantDTO merchantDTO);
    MerchantDTO readMerchantById(Integer merchantId);
    MerchantDTO updateMerchant(MerchantDTO merchantDTO);
    MerchantDTO deleteMerchantById(Integer merchantId);

    MerchantDTO updateMerchantRating(Integer merchantId, float merchantRating);
}
