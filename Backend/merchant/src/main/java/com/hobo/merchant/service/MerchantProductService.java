package com.hobo.merchant.service;

import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.model.MerchantProductDTO;

import java.util.List;

public interface MerchantProductService {
    MerchantProductDTO createMerchantProduct(MerchantProductDTO merchantProductDTO);
    List<MerchantProduct> readMerchantProductById(Integer merchantId);
    MerchantProductDTO updateMerchantProduct(MerchantProductDTO merchantProductDTO);
    MerchantProductDTO deleteMerchantProductById(Integer merchantId, Integer productId);
}
